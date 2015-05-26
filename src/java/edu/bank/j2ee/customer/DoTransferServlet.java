/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee.customer;

import edu.bank.j2ee.Account;
import edu.bank.j2ee.Customer;
import edu.bank.j2ee.Transactions;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author eduardo
 */
@WebServlet(name = "DoTransferServlet", urlPatterns = {"/doTransfer"})
public class DoTransferServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destination = doTransfer(request);
        request.getRequestDispatcher(destination).forward(request, response);
    }
    
    private String doTransfer(HttpServletRequest request){
        String destination="/WEB-INF/customer/doTransfer.jsp";
        
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
        if (cust!=null){
            if(request.getMethod().equals("GET")){
                try{
                Query q = em.createQuery("SELECT a FROM Account a WHERE a.custId.id = :id AND a.status = :status ORDER BY a.timeStamp DESC") ;
                q.setParameter("id", cust.getId());
                q.setParameter("status", "ACTIVE");
                List<Account> accs = q.getResultList();
                request.getSession().setAttribute("accounts", accs);
            }
            catch(Exception e){
                request.setAttribute("flash", e.getMessage());
            }
            return destination;
            }
        }else{
            request.setAttribute("flash", "You are not logged in.");
            return "/login";
            }
        
        
        int faccNum = Integer.parseInt(request.getParameter("facc"));
        int taccNum = Integer.parseInt(request.getParameter("tacc"));
        BigDecimal amount = null;
        try{
            amount = new BigDecimal(request.getParameter("amount"));
        }catch(NumberFormatException nfe){
            request.setAttribute("flash", "Please enter a numerical value as an amount");
            return destination;
        }
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        
        
        try{
            Query q = em.createQuery("SELECT a FROM Account a WHERE a.id = :id");
            q.setParameter("id", faccNum);
            Account facc = (Account)q.getSingleResult();
            Query q1 = em.createQuery("Select a FROM Account a WHERE a.id = :id");
            q.setParameter("id", taccNum);
            Account tacc = (Account)q.getSingleResult();
        
            BigDecimal faccBal = facc.getBalance();
            BigDecimal faccNewBal = faccBal.subtract(amount);
            BigDecimal taccBal = tacc.getBalance();
            BigDecimal taccNewBal = taccBal.add(amount);
            
            int res = amount.compareTo(faccBal);
            if (faccNum==taccNum){
                request.setAttribute("flash", "You cannot transfer to the same account");
                return destination;
            }
            if (res==1){
                request.setAttribute("flash", "You don't have sufficient funds");
                return destination;
            }
            

            facc.setBalance(faccNewBal);
            facc.setBeginBal(faccBal);
            tacc.setBalance(taccNewBal);
            tacc.setBeginBal(taccBal);
            
            Transactions trans1 = new Transactions(facc, amount, type, faccNewBal, description);
            Transactions trans2 = new Transactions(tacc, amount, type, taccNewBal, description);
            
            try{            
                em.getTransaction().begin();
                em.persist(trans1);
                em.persist(trans2);
                em.merge(trans1);
                em.merge(trans2);
                em.getTransaction().commit();
                request.setAttribute("trans1", trans1);    
                request.setAttribute("tacc", tacc);
                request.setAttribute("facc", facc);
                return "/WEB-INF/customer/transaction.jsp";
            }catch (Exception e){
                request.setAttribute("flash", e.getMessage());
                return destination;
            }
        }catch (Exception e){
            request.setAttribute("flash", e.getMessage());
            return destination;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>



}

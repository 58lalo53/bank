/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import java.io.IOException;
import java.math.BigDecimal;
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
 * @author Eduardo
 */
@WebServlet(name = "DoTransactions", urlPatterns = {"/doTransaction"})
public class DoTransactions extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        
        String destination = "/WEB-INF/doTransaction.jsp";
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        Account accs = (Account)request.getSession().getAttribute("accs");
        
        if (cust!=null){
            if(request.getMethod().equals("GET")){
                request.setAttribute("accounts", accs);
                request.getRequestDispatcher(destination).forward(request, response);
                return;
            }
        }else{
            request.setAttribute("flash", "You are not logged in.");
            response.sendRedirect("/bank/login");
            }
        
        String type = request.getParameter("type");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        int accId = Integer.parseInt(request.getParameter("accId"));
        EntityManager em = getEM();
        Query q = em.createQuery("SELECT a FROM Account a WHERE a.id = :id");
        q.setParameter("id", accId);
        Account acc = (Account)q.getSingleResult();
        BigDecimal balance = acc.getBalance();
        String description = request.getParameter("description");
        BigDecimal newBal;
        if(type.equals("deposit")){
            newBal = balance.add(amount);
        }else{
           newBal = balance.subtract(amount);
        }
        acc.setBalance(newBal);
        acc.setBeginBal(balance);
        Transactions trans = new Transactions(acc ,amount ,type ,newBal ,description);
        request.setAttribute("trans", trans);
        try{
            
            em.getTransaction().begin();
            em.persist(trans);
            em.merge(trans);
            em.getTransaction().commit();
            request.setAttribute("trans", trans);    
            request.getRequestDispatcher("/WEB-INF/deposit.jsp").forward(request, response);
            return;
        } catch(Exception e){
            request.setAttribute("flash", e.getMessage());
          }finally{
            request.setAttribute("accounts", accs);
        }
        
        request.getRequestDispatcher(destination).forward(request, response);
        
    }
    EntityManager getEM(){
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        return emf.createEntityManager();
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

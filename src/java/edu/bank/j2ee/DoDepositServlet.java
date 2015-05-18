/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
@WebServlet(name = "DoDepositServlet", urlPatterns = {"/doDeposit"})
public class DoDepositServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destination = "/WEB-INF/customer/doDeposit.jsp";
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
                    
                    }
                request.getRequestDispatcher(destination).forward(request, response);
                return;
            }
        }else{
            request.setAttribute("flash", "You are not logged in.");
            response.sendRedirect("/bank/login");
            }
        
        String type = request.getParameter("type");
        BigDecimal amount;
        try{
            amount = new BigDecimal(request.getParameter("amount"));
        } catch(NumberFormatException nfe){
            request.setAttribute("aflash", "Please enter the amount to deposit");
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
        int accId = Integer.parseInt(request.getParameter("accId"));

        Query q = em.createQuery("SELECT a FROM Account a WHERE a.id = :id");
        q.setParameter("id", accId);
        Account acc = (Account)q.getSingleResult();
        BigDecimal balance = acc.getBalance();
        String description = request.getParameter("description");
        BigDecimal newBal = balance.add(amount);
        acc.setBalance(newBal);
        acc.setBeginBal(balance);
        Transactions trans = new Transactions(acc ,amount ,type ,newBal ,description);
        try{
            
            em.getTransaction().begin();
            em.persist(trans);
            em.merge(trans);
            em.getTransaction().commit();
            
            
            request.setAttribute("trans", trans);   
            
            request.getRequestDispatcher("/deposit").forward(request, response);
            return;
        } catch(Exception e){
            request.setAttribute("flash", e.getMessage());
          }finally{
            
        }
        
        request.getRequestDispatcher(destination).forward(request, response);
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

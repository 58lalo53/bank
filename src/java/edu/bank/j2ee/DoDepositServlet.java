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
        String destination = "/WEB-INF/doDeposit.jsp";
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        
        if (cust!=null){
            if(request.getMethod().equals("GET")){
                request.getRequestDispatcher(destination).forward(request, response);
                return;
            }
        }else{
            request.setAttribute("flash", "You are not logged in.");
            response.sendRedirect("/bank/login");
            }
        
        Account accs = (Account)request.getSession().getAttribute("accs");
        String type = request.getParameter("type");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        int accId = Integer.parseInt(request.getParameter("accId"));
        EntityManager em = getEM();
        Query q = em.createQuery("SELECT a FROM Account a WHERE a.id = :id");
        q.setParameter("id", accId);
        Account acc = (Account)q.getSingleResult();
        BigDecimal balance = acc.getBalance();
        String description = request.getParameter("description");
        BigDecimal newBal = balance.add(amount);
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

    
    private EntityManager getEM(){
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        return emf.createEntityManager();
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

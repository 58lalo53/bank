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
        
        if(request.getMethod().equals("GET")){
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        Account accs = (Account)request.getSession().getAttribute("accs");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        int accId = Integer.parseInt(request.getParameter("accId"));
        Account acc = new Account();
        BigDecimal balance = acc.getBalance();
        String description = request.getParameter("description");
        //BigDecimal newBal = balance.add(amount);
        Transactions trans = new Transactions(acc ,amount ,balance ,description);
        
        EntityManager em = getEM();
        
        try{
            em.getTransaction().begin();
            em.persist(trans);
            em.merge(trans);
            em.getTransaction().commit();
            /*Query q = em.createQuery("SELECT a FROM Account a WHERE a.custId.id = :id ORDER BY a.timeStamp DESC");
            q.setParameter("id", cust.getId());
            List<Account> accs = q.getResultList();
        */
            request.setAttribute("trans", trans);
            request.getRequestDispatcher("/bank/deposit").forward(request, response);
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
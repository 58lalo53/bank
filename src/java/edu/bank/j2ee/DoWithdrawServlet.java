/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "DoWithdrawServlet", urlPatterns = {"/doWithdraw"})
public class DoWithdrawServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String destination = "/WEB-INF/doWithdraw.jsp";
        
        if (request.getMethod().equals("GET")){
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
        
       
        Account accs = (Account)request.getSession().getAttribute("accs");
        
        String type = request.getParameter("type");
        BigDecimal amount;
        try{
            amount = new BigDecimal(request.getParameter("amount"));
        } catch(NumberFormatException nfe){
            request.setAttribute("aflash", "Please enter the amount to withdraw");
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
            
        int accId = Integer.parseInt(request.getParameter("accId"));
        EntityManager em = getEM();
        Query q = em.createQuery("SELECT a FROM Account a WHERE a.id = :id");
        q.setParameter("id", accId);
        Account acc = (Account)q.getSingleResult();
        BigDecimal balance = acc.getBalance();
        
        
        if (amount.compareTo(balance)==1){
            request.setAttribute("flash", "You do not have sufficient funds.");
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
        String description = request.getParameter("description");
        BigDecimal newBal = balance.subtract(amount);
        acc.setBalance(newBal);
        acc.setBeginBal(balance);
        Transactions trans = new Transactions(acc,amount,type,newBal,description);
        request.setAttribute("trans", trans);
        
        try{
            em.getTransaction().begin();
            em.persist(trans);
            em.merge(trans);
            em.getTransaction().commit();
            request.getSession().setAttribute("trans", trans);
            request.getRequestDispatcher("/WEB-INF/withdraw.jsp").forward(request, response);
            return;
        }catch(Exception e){
            request.setAttribute("flash", e.getMessage());
        }finally{
            request.setAttribute("accounts", accs);
        }
        request.getRequestDispatcher(destination).forward(request,response);
    }
    
    private EntityManager getEM(){
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
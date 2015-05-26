/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee.customer;

import edu.bank.j2ee.Customer;
import java.io.IOException;
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
@WebServlet(name = "HomeServlet", urlPatterns = {"/index.html","/home"})
public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        String destination = "/WEB-INF/customer/home.jsp";
        
        
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
        if (request.getMethod().equals("GET")){
            try{
                if (cust!=null){
                Query q = em.createQuery("SELECT a FROM Account a WHERE a.custId.id = :id AND a.status = :status ORDER BY a.timeStamp DESC");
                q.setParameter("id", cust.getId());
                q.setParameter("status", "ACTIVE");
                int accs = q.getResultList().size();
                request.setAttribute("numAcc", accs);
                request.getRequestDispatcher(destination).forward(request, response);
                return;
            }
            else
            request.getRequestDispatcher(destination).forward(request, response);
            } catch(Exception e){
            request.setAttribute("flash", e.getMessage());
        }
        
            if (cust!=null){
                if (cust.getRole().equals("admin")){
                response.sendRedirect("/bank/adminHome");
                return;
            } else{
            request.getRequestDispatcher("/WEB-INF/customer/home.jsp").forward(request, response);
            return;
                }
            }
            
        }
        
        
    try{
            Query q = em.createQuery("SELECT a FROM Account a WHERE a.custId.id = :id AND a.status = :status ORDER BY a.timeStamp DESC");
            q.setParameter("id", request.getSession().getAttribute("custId"));
            q.setParameter("status", "ACTIVE");
            int accs = q.getResultList().size();
            request.setAttribute("numAcc", accs);
            
        } catch(Exception e){
            request.setAttribute("flash", e.getMessage());
        }
            request.getRequestDispatcher("/WEB-INF/customer/home.jsp").forward(request, response);
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

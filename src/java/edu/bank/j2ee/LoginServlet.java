/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destination= "WEB-INF/login.jsp";

        if (request.getMethod().equals("GET")){ 
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        EntityManager em = getEM();
        try {
            Customer cust = (Customer)em.createNamedQuery("Customer.findByUsername")
                    .setParameter("username", username)
                    .getSingleResult();
            if (!cust.getPassword().equals(password))
                throw new Exception("Access Denied");
            request.getSession().setAttribute("cust",cust);
            request.getSession().setAttribute("custId",cust.getId());
            
            response.sendRedirect("/bank/home");
            return;
       } catch (Exception e) {
            request.setAttribute("flash", e.getMessage());
            request.getRequestDispatcher(destination).forward(request, response);
        }

        
    }
        EntityManager getEM() {
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

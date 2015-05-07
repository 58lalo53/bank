/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eduardo
 */
@WebServlet(name = "NewAccServlet", urlPatterns = {"/newAcc"})
public class NewAccServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String destination = "/WEB-INF/newAcc.jsp";
        int rnd = (int)(Math.random()*999999999)+111111111;
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        if (cust == null){
            request.setAttribute("flash", "You are not logged in!");
            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request,response);
            return;
        }
        if (request.getMethod().equals("GET")){
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
        String type = request.getParameter("type");
        String description = request.getParameter("description");
        Account acc = new Account();
        acc.setAccNum(rnd);
        acc.setCustId(cust);
        acc.setDescription(description);
        acc.setType(type);

        EntityManager em = getEM();
        try {
            em.getTransaction().begin();
            em.persist(acc);
            em.merge(acc);
            em.getTransaction().commit();
            request.getSession().setAttribute("acc", acc);
            
        } catch (Exception e) {
            request.setAttribute("flash", e.getMessage());
            response.sendRedirect(destination);
            return;
            }	
        request.getRequestDispatcher("/doAcc").forward(request,response);
            
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

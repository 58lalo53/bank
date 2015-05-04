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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destination="/WEB-INF/register.jsp";
        if (request.getMethod().equals("GET")) request.getRequestDispatcher(destination).forward(request, response);
		String fname=request.getParameter("fname");
                String mname=request.getParameter("mname");
                if (mname.equals("null")){
                    mname="";
                }
		String lname = request.getParameter("lname");
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		
		if (username.length()>10 || username.length()<= 0){
			request.setAttribute("flash", "Username must be between 1 and 10 characters");
                        request.getRequestDispatcher(destination).forward(request,response);
			return;
		}
		if (password.length()>10 || password.length()<= 0){
			request.setAttribute("flash", "Password must be between 1 and 10 characters");
                        request.getRequestDispatcher(destination).forward(request,response);
                        return;
		}
		
		if (!password.equals(password2)){
			request.setAttribute("flash", "Passwords didn't match");
                        request.getRequestDispatcher(destination).forward(request,response);
                        return;
		}
                
                 Customer cust;
		if (!mname.equals("null"))
                    cust = new Customer(fname, lname, mname, street, city, state, zip, phone, email, username, password);
                else
                    cust = new Customer(fname,lname,street,city,state,zip,phone,email,username,password);
		EntityManager em = getEM();
		try {
			em.getTransaction().begin();
			em.persist(cust);
			em.merge(cust);
			em.getTransaction().commit();
			request.getSession().setAttribute("cust", cust);
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
                        return;
		}catch(Exception e){
			request.setAttribute("flash", e.getMessage());
			
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


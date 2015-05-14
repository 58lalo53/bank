package edu.bank.j2ee;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "EditCustServlet", urlPatterns = {"/editCust"})
public class EditCustServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String destination = "/WEB-INF/customer/editCust.jsp";
        
        if (request.getMethod().equals("GET")){
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
        
        String fname=request.getParameter("fname");
        String mname=request.getParameter("mname");
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
        
        if (username.length()>10 || username.length()<=0 || password.length()>10 || password.length()<=0){
            request.setAttribute("flash", "Username and password must be between 1 and 10 characters");
            request.getRequestDispatcher(destination).forward(request, response);
        }
        
        if (!password.equals(password2)){
            request.setAttribute("flash", "Passwords don't match");
            request.getRequestDispatcher(destination).forward(request,response);
            
        }
        
        Customer cust = (Customer)request.getSession().getAttribute("cust");
                 cust.setStreet(street);
                 cust.setCity(city);
                 cust.setState(state);
                 cust.setZip(zip);
                 cust.setPhone(phone);
                 cust.setEmail(email);
        /*if (!mname.equals("null"))
            cust = new Customer(fname, lname, mname, street, city, state, zip, phone, email, username, password);
        else
            cust = new Customer(fname,lname,street,city,state,zip,phone,email,username,password);
        */EntityManager em = getEM();
		
        
        try{
            em.getTransaction().begin();
            em.merge(cust);
            em.getTransaction().commit();
            request.getSession().setAttribute("cust", cust);
            request.setAttribute("flash", "Your info has been updated");
            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
            return;
        } catch (Exception e){
            request.setAttribute("flash", e.getMessage());
        }
        request.getRequestDispatcher(destination).forward(request, response);
        
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

    private EntityManager getEM() {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        return emf.createEntityManager();
    }

}

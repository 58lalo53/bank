package edu.bank.j2ee.admin;

import edu.bank.j2ee.Customer;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lpz_l_000
 */
@WebServlet(name = "CreateAdminServlet", urlPatterns = {"/createAdmin"})
public class CreateAdminServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String destination = createAdmin(request);
        if (destination.equals("/bank/adminHome")){
            response.sendRedirect(destination);
            return;
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }
    
    private String createAdmin(HttpServletRequest request){
        String destination = "/WEB-INF/admin/register.jsp";
        
        if (request.getMethod().equals("GET")) 
            return destination;
        
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
        String role = "admin";
        
        if (username.length()< 6 || username.length() > 10 || password.length() >10 || password.length() < 6){
            request.setAttribute("flash", "Your username and password must be between 6 and 10 characters");
            return destination;
        }
        
        if (!password.equals(password2)){
            request.setAttribute("flash", "Your passwords don't match");
            return destination;
        }
        
        Customer cust;
        
        if (mname!=null)
            cust = new Customer(fname,lname,mname,street,city,state,zip,phone,email,username,password, role);
        else 
            cust = new Customer(fname,lname,street,city,state,zip,phone,email,username,password, role);
        
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
        Customer cust1;
        
        try{
            try{
                Query q = em.createNamedQuery("Customer.findByUsername");
                q.setParameter("username", username);
                cust1 = (Customer)q.getSingleResult();
            if (cust1 != null){
                request.setAttribute("flash", "Username already exists");
                return destination;
            }
            }catch(NoResultException nre){
                em.getTransaction().begin();
                em.persist(cust);
                em.merge(cust);
                em.getTransaction().commit();
                return "/bank/adminHome";
            }
            
        }catch(Exception e){
            request.setAttribute("flash", e.getMessage());
            return destination;
        }
        return destination;
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

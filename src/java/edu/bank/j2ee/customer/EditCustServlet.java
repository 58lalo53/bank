package edu.bank.j2ee.customer;

import edu.bank.j2ee.Customer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Eduardo
 */
@MultipartConfig
@WebServlet(name = "EditCustServlet", urlPatterns = {"/editCust"})
public class EditCustServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String destination = editCust(request);
        request.getRequestDispatcher(destination).forward(request, response);
    }
        
    private String editCust(HttpServletRequest request) throws IOException, ServletException{
        String destination = "/WEB-INF/customer/editCust.jsp";
        
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        
        if (request.getMethod().equals("GET")){
            return destination;
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
        
        final Part filePart = request.getPart("pic");
        long size = filePart.getSize();
        String filename = filePart.getSubmittedFileName();
        String filetype = filePart.getContentType();
        
        InputStream imgdata = filePart.getInputStream();
        byte[] pixels = readImage(imgdata);   

        

        if (!filetype.contains("image")&& !request.getParameter("pic").isEmpty()) {
                request.setAttribute("flash", "The uploaded file is not an image!");
                return destination;
        }
        
        if (username.length()>10 || username.length()<=0 || password.length()>10 || password.length()<=0){
            request.setAttribute("flash", "Username and password must be between 1 and 10 characters");
            return destination;
        }
        
        if (!password.equals(password2)){
            request.setAttribute("flash", "Passwords don't match");
            return destination;
            
        }
        cust.setStreet(street);
        cust.setCity(city);
        cust.setState(state);
        cust.setZip(zip);
        cust.setPhone(phone);
        cust.setEmail(email);
        if (size!=0){
            cust.setPicture(pixels);
            cust.setPictype(filetype);
        }
        
                 
                 
                 
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
            return "/home";
        } catch (Exception e){
            request.setAttribute("flash", e.getMessage());
        }
        return destination;
        
    }
    
    
    
    private byte[] readImage(InputStream imgdata) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[0xFFFF];
        for (int len; (len = imgdata.read(buffer)) != -1;)
            os.write(buffer, 0, len);
        os.flush();
        return os.toByteArray();        
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

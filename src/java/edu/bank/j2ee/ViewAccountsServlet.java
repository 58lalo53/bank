package edu.bank.j2ee;

import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lpz_l_000
 */
@WebServlet(name = "ViewAccountsServlet", urlPatterns = {"/viewAccounts"})
public class ViewAccountsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String destination = viewAccounts(request);
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String viewAccounts(HttpServletRequest request) {
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        
        if (cust.getRole().equals("customer")){
            request.setAttribute("flash", "You do not have access");
            return "/home";
        }
        
        try{
            List<Account> accs = em.createNamedQuery("Account.findAll").getResultList();
            request.getSession().setAttribute("accs", accs);
        }catch(Exception e){
            request.setAttribute("flash", e.getMessage());
        }
        return "/WEB-INF/admin/viewAccount.jsp";
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

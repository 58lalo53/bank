package edu.bank.j2ee;

import java.io.IOException;
import java.util.Date;
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
 * @author lpz_l_000
 */
@WebServlet(name = "CloseAccServlet", urlPatterns = {"/closeAcc"})
public class CloseAccServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destination = "/WEB-INF/closeAcc.jsp";
        
        if (request.getMethod().equals("GET")){
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
        
        String[] ids = request.getParameterValues("accId");
        int[] id = new int[ids.length]; 
        int i = 0;
        for(String str : ids){
            id[i]=Integer.parseInt(str.trim());
            i++;
        }
        EntityManager em = getEM();
        Query q = null;
        Account acc = null;
        List<Account> accs = null;
        try{
            for(int x = 0; x<id.length; x++){
                
                q = em.createQuery("SELECT a FROM Account a WHERE a.id = :id");
                q.setParameter("id", id[x]);
                acc = (Account)q.getSingleResult();
                acc.setStatus("INACTIVE");
                
                em.getTransaction().begin();
                em.merge(acc);
                em.getTransaction().commit();
            }
                     
            
            request.setAttribute("flash", "You have successfully closed your account(s)");
            request.getRequestDispatcher("/accounts").forward(request, response);
            return;
        } catch(Exception e){
            request.setAttribute("flash", e.getMessage());
        }
        request.getRequestDispatcher(destination).forward(request,response);
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

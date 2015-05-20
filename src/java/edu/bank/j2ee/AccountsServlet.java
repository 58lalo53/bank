package edu.bank.j2ee;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AccountsServlet", urlPatterns = {"/accounts"})
public class AccountsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Customer cust = (Customer)request.getSession().getAttribute("cust");
        
        if (cust == null){
            request.setAttribute("flash", "You are not logged in");
            request.getRequestDispatcher("/home").forward(request, response);
        }

        int page=1;
        if (request.getParameter("page")!=null)
            page = Integer.parseInt(request.getParameter("page"));
        int accPerPage = 5;
        int numOfAccs;
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        try{
            Query q = em.createQuery("SELECT a FROM Account a WHERE a.custId.id = :id AND a.status = :status ORDER BY a.timeStamp DESC") ;
            q.setParameter("id", cust.getId());
            q.setParameter("status", "ACTIVE");
            numOfAccs = q.getResultList().size();
            q.setFirstResult((page-1)*accPerPage);
            q.setMaxResults(accPerPage);
            List<Account> accs = q.getResultList();
            int numOfPages = (int)Math.ceil(numOfAccs*1.0/accPerPage);
            request.setAttribute("numOfPages", numOfPages);
            request.setAttribute("numOfAccs", numOfAccs);
            request.setAttribute("curPage", page);
            request.getSession().setAttribute("accounts", accs);
        } catch(Exception e){
            request.setAttribute("flash", e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/customer/accounts.jsp").forward(request, response);
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

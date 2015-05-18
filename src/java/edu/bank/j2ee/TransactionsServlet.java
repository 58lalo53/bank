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
@WebServlet(name = "TransactionsServlet", urlPatterns = {"/transactions"})
public class TransactionsServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String destination = transaction(request);
        request.getRequestDispatcher(destination).forward(request, response);
    }
    
    private String transaction(HttpServletRequest request){
        String destination = "/WEB-INF/customer/transactions.jsp";
        
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        
        int page = 1;
        int transPerPage = 6;
        
        if (request.getParameter("page")!=null)
            page = Integer.parseInt(request.getParameter("page"));
        
        if (cust==null){
            request.setAttribute("flash", "You are not logged in");
            return "../login.jsp";
        }
                
        int accId = Integer.parseInt(request.getParameter("accId"));
        
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
        
        try{
            Query q1 = em.createQuery("SELECT a FROM Account a WHERE a.id = :id");
            q1.setParameter("id", accId);
            Account acc1 = (Account)q1.getSingleResult();
            Query q2 = em.createQuery("SELECT t FROM Transactions t WHERE t.accId = :id ORDER BY t.timeStamp DESC");
            q2.setParameter("id", acc1);
            int numOfTrans = q2.getResultList().size();
            q2.setFirstResult((page-1)*transPerPage);
            q2.setMaxResults(transPerPage);
            List<Transactions> trans = q2.getResultList();
            
            int numOfPages = (int)Math.ceil(numOfTrans*1.0/transPerPage);
            //request.setAttribute("acc", acc1.getId());
            request.setAttribute("accId", accId);
            request.setAttribute("curPage", page);
            request.setAttribute("numOfTrans", numOfTrans);
            request.setAttribute("numOfPages", numOfPages);
            request.setAttribute("trans", trans);
            request.setAttribute("acc", acc1);
        }catch(Exception e){
            request.setAttribute("flash", e.getMessage());
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
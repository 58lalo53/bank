package edu.bank.j2ee.admin;

import edu.bank.j2ee.Customer;
import java.io.IOException;
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
@WebServlet(name = "ViewCustomersServlet", urlPatterns = {"/viewCust"})
public class ViewCustomersServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String destination = viewCust(request);
        
        if ( destination.equals("/bank/login") || destination.equals("/bank/home")){
            response.sendRedirect(destination);
            return;
        }
        request.getRequestDispatcher(destination).forward(request, response);
        
    }
    
    private String viewCust(HttpServletRequest request){
        
        Customer cust = new Customer();
        
        if (request.getSession().getAttribute("cust")!=null)
            cust = (Customer)request.getSession().getAttribute("cust");
        else
            return "/bank/login";
        
        String destination = "/WEB-INF/admin/viewCust.jsp";
        int page = 1;
        
        if (cust.getRole().equals("customer")){
            return "/bank/home";
        }
        
        EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        
        
        String order = request.getParameter("order");
            if (order==null)
                order = "id";
        String orderBy = "";
            switch (order){
                case "id":
                    orderBy = "ORDER BY c.id";
                    break;
                case "null":
                    orderBy = "";
                    break;
                case "fname":
                    orderBy="ORDER BY c.fname";
                    break;
                case "lname":
                    orderBy = "ORDER BY c.lname";
                    break;
                case "mname":
                    orderBy = "ORDER BY c.mname";
                    break;
            
        }
            
            
        int custPerPage = 5;

        if(request.getParameter("page")!=null)
            page = Integer.parseInt(request.getParameter("page"));
            
        int numOfCusts;

        try{
            
            String query = "SELECT c FROM Customer c WHERE c.role = :role "+orderBy;
            Query q = em.createQuery(query);
            q.setParameter("role", "customer");
            numOfCusts = q.getResultList().size();
            q.setFirstResult((page-1)*custPerPage);
            q.setMaxResults(custPerPage);
            
            List<Customer> custs = q.getResultList();
                
            int numOfPages = (int)Math.ceil(numOfCusts * 1.0/custPerPage);
            
            request.setAttribute("numOfCusts", numOfCusts);
            request.setAttribute("numOfPages", numOfPages);
            request.setAttribute("curPage", page);
            request.setAttribute("custs", custs);
            
        }catch(Exception e){
            request.setAttribute("flash", e.getMessage());
            return "/adminHome";
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

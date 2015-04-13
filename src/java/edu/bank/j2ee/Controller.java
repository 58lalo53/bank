package edu.bank.j2ee;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 58lalo53
 */
public class Controller extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String action=request.getParameter("action");
		if(action==null)
			action="login";
		String destination;
		switch (action) {
                    default:
                    case "home": 
                        destination = home(request);
			break;
                    case "login":
                        destination = login(request);
                        break;
                    case "logout":
                        destination = logout(request);
                        break;
                    case "register": 
                        destination = register(request);
			break;
                    case "accounts":
                        destination = accounts(request);
                        break;
                    case "newAcc":
			destination = newAcc(request);
			break;
                    case "doAcc":
                        destination = doAcc(request);
                        break;
                    case "doDeposit":
                        destination = doDeposit(request);
                        break;
                    case "doWithdraw":
                        destination = doWithdraw(request);
                        break;
		}

            request.getRequestDispatcher(destination + ".jsp").forward(request, response);
	}
	
	private String home(HttpServletRequest request)throws ServletException{
            try{
            Account accs = (Account)request.getSession().getAttribute("accounts");
            request.setAttribute("accounts", accs);
        } catch(Exception e){
            request.setAttribute("flash", e.getMessage());
        }
            return "home";
	}
        
        private String doDeposit(HttpServletRequest request) throws ServletException{
            if (request.getMethod().equals("GET")) return "doDeposit";
            Customer cust = (Customer)request.getSession().getAttribute("cust");
            Account accs = (Account)request.getSession().getAttribute("accounts");
            BigDecimal amount = new BigDecimal(request.getParameter("amount"));
            BigDecimal balance = new BigDecimal(request.getParameter("balance"));
            Account accId = new Account();
            String description = request.getParameter("description");
            Transactions trans = new Transactions(amount, accId, description);
            BigDecimal newBal = balance.add(amount);
            EntityManager em = getEM();
            try{
                em.getTransaction().begin();
                em.persist(trans);
                em.merge(trans);
                em.getTransaction().commit();
                request.setAttribute("trans", trans);
                return deposit(request);     
            } catch(Exception e){
                request.setAttribute("flash", e.getMessage());
              }finally{
           request.setAttribute("accounts", accs);
            }
            return "doDeposit";
        }
        
        private String deposit(HttpServletRequest request) throws ServletException{
            return "deposit";
        }
        
        private String doWithdraw(HttpServletRequest request) throws ServletException{
            return "doWithdraw";
        }
        
        private String accounts(HttpServletRequest request) throws ServletException{
            Customer cust = (Customer)request.getSession().getAttribute("cust");
            EntityManager em = getEM();
            try{
                Query q = em.createQuery("SELECT a FROM Account a WHERE a.custId.id = :id ORDER BY a.timeStamp DESC");
                q.setParameter("id", cust.getId());
                List<Account> accs = q.getResultList();
                request.getSession().setAttribute("accounts", accs);
            } catch(Exception e){
                request.setAttribute("flash", e.getMessage());
            }
            return "accounts";
        }
	
	private String register(HttpServletRequest request)throws ServletException{
            Connection conn = null;
            Statement stmt = null;
            if (request.getMethod().equals("GET")) return "register";
		String fname=request.getParameter("fname");
                String mname="";
                if (!request.getParameter("mname").equals("null")){
                    mname=request.getParameter("mname");
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
			return "register";
		}
		if (password.length()>10 || password.length()<= 0){
			request.setAttribute("flash", "Password must be between 1 and 10 characters");
			return "register";
		}
		
		if (!password.equals(password2)){
			request.setAttribute("flash", "Passwords didn't match");
			return "register";
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
			return home(request);
		}catch(Exception e){
			request.setAttribute("flash", e.getMessage());
			return "register";
		}
	
	}
	
	private String newAcc(HttpServletRequest request) throws ServletException{
            Customer cust = (Customer)request.getSession().getAttribute("cust");
            if (cust == null){
                request.setAttribute("flash", "You are not logged in!");
                return "home";
            }
            if (request.getMethod().equals("GET")) return "newAcc";
            String type = request.getParameter("type");
            String description = request.getParameter("description");
            Account acc = new Account();
            acc.setType(type);
            acc.setDescription(description);
            acc.setCustId(cust);
            cust.setAccNum(acc);
            EntityManager em = getEM();
            try {
                em.getTransaction().begin();
                em.persist(acc);
                em.merge(acc);
                em.getTransaction().commit();
                request.getSession().setAttribute("account", acc);

            } catch (Exception e) {
                request.setAttribute("flash", e.getMessage());
                return "newAcc";
            }	
            return doAcc(request);
	}
        
        private String doAcc(HttpServletRequest request) throws ServletException{
            return "doAcc";
        }
        
        private String transactions(HttpServletRequest request) throws ServletException{
            Customer cust = (Customer)request.getSession().getAttribute("cust");
            if (cust==null){
                request.setAttribute("flash", "You are not logged in");
                return "login";
            }
            return "transaction";
        }
        
        	private String login(HttpServletRequest request) throws ServletException{
            if (request.getMethod().equals("GET")) return "login";
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            EntityManager em = getEM();
            try {
                Customer cust = (Customer)em.createNamedQuery("Customer.findByUsername")
                        .setParameter("username", username)
                        .getSingleResult();
                if (!cust.getPassword().equals(password))
                    throw new Exception("Access Denied");
                request.getSession().setAttribute("cust",cust);
                request.getSession().setAttribute("custId",cust.getId());
                return home(request);
            } catch (Exception e) {
                request.setAttribute("flash", e.getMessage());
                return "login";
            }
        }
        
        private String logout(HttpServletRequest request) throws ServletException{
            request.getSession().removeAttribute("cust");
            return "home";
        }
        
        private EntityManager getEM() {
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

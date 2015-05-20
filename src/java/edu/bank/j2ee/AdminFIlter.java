/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lpz_l_000
 */
@WebFilter(filterName = "AdminFIlter", urlPatterns = {"/"})
public class AdminFIlter implements Filter {
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        
        if (cust.getRole().equals("admin"))
            response.sendRedirect("bank/adminHome");
        else
                chain.doFilter(request, response);
        
        
        
    }
        
    @Override
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) {}
        
    
    
}

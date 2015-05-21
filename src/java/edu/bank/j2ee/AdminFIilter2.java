/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import java.io.IOException;
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
@WebFilter(filterName = "AdminFIilter2", urlPatterns = {"/adminHome","/viewCust", "/viewAccounts"})
public class AdminFIilter2 implements Filter {
    
   
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        
        Customer cust = (Customer)request.getSession().getAttribute("cust");
        if (!cust.getRole().equals("admin") )
            response.sendRedirect("/bank/home");
        else
            chain.doFilter(req, res);
    }
    @Override
    public void destroy() {        
    }

    @Override
    public void init(FilterConfig filterConfig) {}
}
    
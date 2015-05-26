/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lpz_l_000
 */
public class DecimalCheck {
    static void checkDecPlace(HttpServletRequest request, HttpServletResponse response,String destination) throws ServletException, IOException{
        String amount = request.getParameter("amount");
        int i = amount.lastIndexOf(".");
        if (amount.substring(i+1).length()>2){
            request.setAttribute("flash", "You cannot eneter more than 2 decimal places");
            request.getRequestDispatcher(destination).forward(request, response);
            return;
        }
    }
}

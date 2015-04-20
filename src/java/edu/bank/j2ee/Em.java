/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.bank.j2ee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;


public class Em extends HttpServlet{
            EntityManager getEM() {
            EntityManagerFactory emf = (EntityManagerFactory)getServletContext().getAttribute("emf");
            return emf.createEntityManager();
        }
}

/*
 *Developed by : Subhradip Biswas  
 *Date: jan 10, 2021. 
 */
package com.controller;

import com.bean.loginBean;
import com.dao.daoImpl;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author u
 */
public class loginController extends HttpServlet {
    private Gson gson = new Gson();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            System.out.println("hello we are in login controller !");
            String uid=request.getParameter("uid");
            String pass=request.getParameter("pass");
            
            System.out.println("uid: "+uid +" pass: "+pass);       
            String userrole="1";
            String userStatus="test";
            if(userrole.equalsIgnoreCase("1")){
                userStatus="admin";
            }else if(userrole.equalsIgnoreCase("2")){
                userStatus="user";
            }
            
            
            daoImpl dao=new daoImpl();
                      
            loginBean loginBean= new loginBean();
            loginBean=dao.verifyLogin(uid, pass);
            //put this code on impl file
            loginBean.setUserStatus(userStatus);
            
            
            String employeeJsonString = this.gson.toJson(loginBean);
       //String test="{\"status\": \"success\"}"; 
            //PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
           // System.out.println(""+employeeJsonString.toString());
            out.print(employeeJsonString);
            out.flush(); 
            
        }
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

/*
 *Developed by : Subhradip Biswas  
 *Date: jan 10, 2021. 
 */
package com.controller;

import com.bean.NewUserRegistrationBean;
import com.bean.message;
import com.commons.commons;
import com.dao.daoImpl;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author u
 */
public class newRegistrationController extends HttpServlet {
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
           String url=null;
           String webElement=request.getParameter("webElement");
           String fname=request.getParameter("fname");
           String lname=request.getParameter("lname");
           String email=request.getParameter("email");
           String phno=request.getParameter("phno");
           String password=request.getParameter("password");
            
           daoImpl imp;
           NewUserRegistrationBean newUserRegistrationBean;
           commons cc;
           
           message message=new message();
           if(webElement.equalsIgnoreCase("view")){
            url = "register.html";
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
            
           }else if(webElement.equalsIgnoreCase("registration")){
               System.out.println("we are here in new registration !");
               boolean submitSuccessFlag=false;
               cc=new commons();
               //cc.getSystemYear();
               //cc.getLastUserRegistrationInteger();
               
               imp=new daoImpl();
               newUserRegistrationBean=new NewUserRegistrationBean();
               newUserRegistrationBean.setFname(fname);
               newUserRegistrationBean.setLname(lname);
               newUserRegistrationBean.setEmail(email);
               newUserRegistrationBean.setPassword(password);
               newUserRegistrationBean.setPhno(phno);
               newUserRegistrationBean.setProfileId(cc.getNewProfileId());
               
               boolean status= false;
               status=imp.saveNewRegistrationDetails(newUserRegistrationBean);
               
              if(status == true){
                  imp.saveNewLoginDetails(newUserRegistrationBean);
              }
               
               message.setSuccessMessage("success");
               
           }
           String messageData = this.gson.toJson(message);
        
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            //System.out.println(""+userLocationJsonString.toString());
            out.print(messageData);
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

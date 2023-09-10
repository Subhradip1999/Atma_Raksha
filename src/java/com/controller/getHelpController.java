/*
 *Developed by : Subhradip Biswas  
 *Date: jan 10, 2021. 
 */
package com.controller;

import com.bean.userCurrentLocation;
import com.dao.daoImpl;
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
public class getHelpController extends HttpServlet {

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
            
            
            String userIdString =request.getParameter("nearUserUserId");
            String userId=request.getParameter("userId");
            String lat=request.getParameter("lat");
            String lng=request.getParameter("lng");
            String name=request.getParameter("name");
            
            System.out.println("==============>"+userIdString);
            
            String helpArr[]=userIdString.split("@");
            daoImpl imp=new daoImpl();
            
            userCurrentLocation u=new userCurrentLocation();
            u.setUid(userId);
            u.setName(name);
            u.setLat(lat);
            u.setLng(lng);
            for(int i=0;i<helpArr.length;i++){
                u.setProfileId(helpArr[i]);
                imp.setFlag(helpArr[i]);
                imp.insertDataInNearUserAlarm(u);
            }
            
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

/*
 *Developed by : Subhradip Biswas  
 *Date: jan 10, 2021. 
 */
package com.controller;

import com.bean.userCurrentLocation;
import com.dao.daoImpl;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author u
 */
public class userDashboardController extends HttpServlet {
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
       // response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           String profileId=request.getParameter("profileId");
           daoImpl imp=new daoImpl();
           ArrayList<userCurrentLocation> getUserLocation = new ArrayList<userCurrentLocation>();
          // dummy test
          
          userCurrentLocation user=new userCurrentLocation();
//            user.setUid("0000");
//            user.setName("dummy");
//            user.setLat("00.00");
//            user.setLng("00.00");
            //imp.insertDataInNearUserAlarm(user);
            //for testing purpose
            
           // imp.updateFlag(profileId);
            // test ends
           
           getUserLocation = imp.getData(profileId);
           
           String userLocationJsonString = this.gson.toJson(getUserLocation);
        
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            //System.out.println(""+userLocationJsonString.toString());
            out.print(userLocationJsonString);
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
//    private void setAccessControlHeaders(HttpServletResponse resp) {
//        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
//        resp.setHeader("Access-Control-Allow-Methods", "GET");
//        //resp.setHeader("Access-Control-Allow-Methods", "POST");
//    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Connect.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author sonbu
 */
public class Likes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Like</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Like at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setAttribute("currentTimeMillis", System.currentTimeMillis());
        PrintWriter out = response.getWriter();
        Connection connection = null;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String postID = request.getParameter("postID");
        int userID = 0;
        String location = request.getParameter("location");
        try {
            connection = Connect.getConnection();
            PreparedStatement stmUID = connection.prepareStatement("select userid from users where username=?");
            stmUID.setString(1, username);
            ResultSet userIDs = stmUID.executeQuery();
            if (userIDs.next()) {
                userID = userIDs.getInt(1);
                System.out.println(userID);
            }
            ResultSet rs;
            PreparedStatement stm;
            String buttonState;
            stm = connection
                    .prepareStatement("select * from likes where userid=(select userid from users where username=?)");
            stm.setString(1, username);
            rs = stm.executeQuery();
            if (rs.next()) {
                stm = connection.prepareStatement("delete from likes where userid=? and postid=?");
                stm.setInt(1, userID);
                stm.setString(2, postID);
            } else {
                stm = connection.prepareStatement("insert into likes(postid,userid) values (?,?)");
                stm.setString(1, postID);
                stm.setInt(2, userID);
            }
            int success = stm.executeUpdate();
            if (location.equals("inside")) {
                response.sendRedirect("Inside");
            } else if (location.equals("friendInside")) {
                String friendName = request.getParameter("friendname");
                response.sendRedirect("viewProfile?friendname=" + URLEncoder.encode(friendName, "UTF-8"));
            }
        } catch (Exception e) {
            out.println("<h1>no</h1>");
            System.out.println(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    // handle any errors
                }
            }
        }
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

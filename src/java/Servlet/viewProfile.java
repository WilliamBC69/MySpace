/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Connect.*;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sonbu
 */
public class viewProfile extends HttpServlet {

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
            out.println("<title>Servlet viewProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet viewProfile at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html");
        request.setAttribute("currentTimeMillis", System.currentTimeMillis());
        PrintWriter out = response.getWriter();
        Connection connection = null;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String friendName = request.getParameter("friendname");
        try {
            connection = Connect.getConnection();
            String query="SELECT * FROM Posts WHERE userid = (SELECT userid FROM Users WHERE username = ?)";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1,friendName);
            ResultSet rs = stm.executeQuery();

            List<Map<String, Object>> posts = new ArrayList<>();// make a list of posts
            while (rs.next()) {
                Map<String, Object> post = new HashMap<>();// make map to represent post
                post.put("content", rs.getString("content"));
                post.put("date", rs.getDate("date"));
                posts.add(post);
            }

            Collections.reverse(posts);
            request.setAttribute("posts", posts);// include the list into request
            request.setAttribute("friendname", friendName);

            RequestDispatcher dispatcher = request.getRequestDispatcher("friendProfile.jsp");//send the user and the list to friendProfile.jsp
            dispatcher.forward(request, response);
        } catch (Exception e) {
            out.println("<h1>no</h1>");
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Connect.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sonbu
 */
public class Friend extends HttpServlet {

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
            out.println("<title>Servlet Friend</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Friend at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        Connection connection=null;
        try {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            String query = "(SELECT u2.username AS friendname FROM Friends f JOIN Users u1 ON f.userid1 = u1.userid JOIN Users u2 ON f.userid2 = u2.userid WHERE u1.username = ?) UNION (SELECT u1.username AS friendname FROM Friends f JOIN Users u1 ON f.userid1 = u1.userid JOIN Users u2 ON f.userid2 = u2.userid WHERE u2.username = ?)";//get friends id by joining result table where the userid is userid1 and userid is userid2 the get the names of users with that id
            connection = Connecting.getConnection();
            PreparedStatement userStmt = connection.prepareStatement(query);
            userStmt.setString(1, username);
            userStmt.setString(2, username);
            ResultSet rs = userStmt.executeQuery();

            List<Map<String, Object>> friends = new ArrayList<>();//list of friends
            while (rs.next()) {
                Map<String, Object> friend = new HashMap<>();
                friend.put("friendname", rs.getString("friendname"));
                friends.add(friend);
            }

            Collections.reverse(friends);

            request.setAttribute("friends", friends);

            RequestDispatcher dispatcher = request.getRequestDispatcher("friend.jsp");//send list and user to friend.jsp
            dispatcher.forward(request, response);

        } catch (Exception e) {
            out.println("<h1>no</h1>");
        }finally {
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

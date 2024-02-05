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

import Connect.Connecting;
import Connect.Connect;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author sonbu
 */
public class newFriend extends HttpServlet {

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
            out.println("<title>Servlet addFriend</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addFriend at " + request.getContextPath() + "</h1>");
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
        String searchuser = request.getParameter("searchuser");
        Connection connection = null;
        try {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");

            connection = Connecting.getConnection();
            PreparedStatement userStmt = connection.prepareStatement(
                    "SELECT username AS newfriendname FROM Users WHERE username like ? AND userid NOT IN (SELECT userid2 FROM Friends WHERE userid1 = (SELECT userid FROM Users WHERE username = ?) UNION SELECT userid1 FROM Friends WHERE userid2 = (SELECT userid FROM Users WHERE username = ?))");
            userStmt.setString(1, "%" + searchuser + "%");
            userStmt.setString(2, username);
            userStmt.setString(3, username);
            ResultSet rs = userStmt.executeQuery();

            List<Map<String, Object>> newfriends = new ArrayList<>();// list of potential friends
            while (rs.next()) {
                Map<String, Object> newfriend = new HashMap<>();
                newfriend.put("newfriendname", rs.getString("newfriendname"));
                newfriends.add(newfriend);
            }

            Collections.reverse(newfriends);
            request.setAttribute("newfriends", newfriends);

            RequestDispatcher dispatcher = request.getRequestDispatcher("newfriend.jsp");// send list and user to friend.jsp
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Error occurred: " + e.getMessage() + "</h1>");
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

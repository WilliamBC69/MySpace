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
public class Inside extends HttpServlet {

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
            out.println("<title>Servlet Inside</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Inside at " + request.getContextPath() + "</h1>");
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
        Connection connection=null;
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        try {
            connection = Connect.getConnection();
            String query = "SELECT * FROM Posts WHERE userid = (SELECT userid FROM Users WHERE username = ?)";//get posts belonging to user
            PreparedStatement userStmt = connection.prepareStatement(query);
            userStmt.setString(1, username);
            ResultSet rs = userStmt.executeQuery();

            List<Map<String, Object>> posts = new ArrayList<>();//make a list of posts
            while (rs.next()) {
                Map<String, Object> post = new HashMap<>();//make map to represent post
                post.put("postID",rs.getInt("postid"));
                post.put("content", rs.getString("content"));
                post.put("date", rs.getDate("date"));
                PreparedStatement likeStatement=connection.prepareStatement("Select count(*) as likeNum from likes where postid=?");
                likeStatement.setInt(1, rs.getInt("postid"));
                ResultSet rsl=likeStatement.executeQuery();
                if(rsl.next()){
                    post.put("likes",rsl.getInt("likeNum"));
                }else{
                    post.put("likes",0);
                }
                posts.add(post);
            }

            Collections.reverse(posts);//reverse to print old to new

            request.setAttribute("posts", posts);//include the list into request
            request.setAttribute("buttonState",request.getParameter("buttonState"));

            RequestDispatcher dispatcher = request.getRequestDispatcher("inside.jsp");//send the user and the list to inside.jsp
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

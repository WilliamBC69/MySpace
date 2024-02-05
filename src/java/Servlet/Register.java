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

import Connect.Connect;
import Connect.Connecting;
import PassHash.PassHash;
import PassHash.Salt;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author sonbu
 */
public class Register extends HttpServlet {

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
            out.println("<title>Servlet Register</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        Connection con=null;

        try {
            con = Connecting.getConnection();

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            // handle register
            PreparedStatement st = con.prepareStatement("Select username from Users where username=?");// check if user
                                                                                                       // already exist
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                String salt = Salt.generate();
                String hashedPass = PassHash.hashPass(password, salt);
                st = con.prepareStatement("INSERT INTO Users (username, password, salt) VALUES (?, ?, ?)");// insert
                                                                                                           // user
                st.setString(1, username);
                st.setString(2, hashedPass);
                st.setString(3, salt);
                st.executeUpdate();
                // Set the attribute
                request.setAttribute("RegisterSuccess", "Registered successfully");
                // Forward the request to register.jsp
                request.getRequestDispatcher("register.jsp").forward(request, response); // success
            } else {
                request.setAttribute("RegisterError", "User already exist");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                // user already exist
            }

        } catch (Exception e) {
            out.println("<h1>no</h1>");
        } finally {
            if (con != null) {
                try {
                    con.close();
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

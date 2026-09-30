package com.aijob;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException {

        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO users(username,password,email) VALUES(?,?,?)");

            ps.setString(1, user);
            ps.setString(2, pass);
            ps.setString(3, email);

            ps.executeUpdate();

            res.sendRedirect("index.html");

        } catch(Exception e) {
            e.printStackTrace();
            res.getWriter().println("Registration failed"+e.getMessage());
        }
    }
}
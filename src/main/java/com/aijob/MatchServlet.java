package com.aijob;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.util.*;

@WebServlet("/MatchServlet")
public class MatchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");

        String skills = req.getParameter("skills");
        if (skills == null || skills.trim().isEmpty()) {
            skills = "";
        }

        Map<String, Integer> jobs = JobMatcher.matchJobs(skills);

        PrintWriter out = res.getWriter();

        out.println("<html><head><title>AI Matched Jobs</title>");
        out.println("<style>");
        out.println("body{font-family:Arial;background:#1e1e2f;color:white;text-align:center;}");
        out.println(".card{background:#2b2b4f;padding:20px;margin:15px auto;width:320px;border-radius:10px;box-shadow:0 0 10px rgba(0,0,0,0.5);}");
        out.println(".percent{font-size:18px;color:#6c63ff;margin-top:10px;}");
        out.println("a{color:#6c63ff;text-decoration:none;}");
        out.println("</style></head><body>");

        out.println("<h2>AI Matched Jobs</h2>");

        if (jobs == null || jobs.isEmpty()) {
            out.println("<p>No matching jobs found</p>");
        } else {
            for (Map.Entry<String, Integer> entry : jobs.entrySet()) {
                out.println("<div class='card'>");
                out.println("<h3>" + entry.getKey() + "</h3>");
                out.println("<p>Matched based on your skills</p>");
                out.println("<div class='percent'>Match: " + entry.getValue() + "%</div>");
                out.println("</div>");
            }
        }

        out.println("<br><a href='dashboard.html'>Back</a>");
        out.println("</body></html>");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doPost(req, res);
    }
}
package com.aijob;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;

@WebServlet("/UploadServlet")
@MultipartConfig
public class UploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        Part filePart = req.getPart("resume");
        InputStream fileContent = filePart.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
        StringBuilder text = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            text.append(line.toLowerCase()).append(" ");
        }

        
        String extractedSkills = extractSkills(text.toString());

      
        res.sendRedirect("MatchServlet?skills=" + extractedSkills);
    }

    private String extractSkills(String resumeText) {

       
        String[] skillsDB = {
            "java", "sql", "python", "html", "css", "javascript"
        };

        StringBuilder foundSkills = new StringBuilder();

        for(String skill : skillsDB) {
            if(resumeText.contains(skill)) {
                foundSkills.append(skill).append(",");
            }
        }

        return foundSkills.toString();
    }
}
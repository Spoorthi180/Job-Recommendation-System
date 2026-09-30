package com.aijob;

import java.sql.*;
import java.util.*;

public class JobMatcher {

    // Returns a map of job title -> match percentage
    public static Map<String, Integer> matchJobs(String userSkills) {
        Map<String, Integer> matchedJobs = new LinkedHashMap<>();

        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT j.title, s.skill_name FROM jobs j " +
                           "JOIN job_skills js ON j.job_id=js.job_id " +
                           "JOIN skills s ON js.skill_id=s.skill_id";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            // Build a map: job -> required skills
            Map<String, List<String>> jobSkillMap = new HashMap<>();
            while (rs.next()) {
                String job = rs.getString("title");
                String skill = rs.getString("skill_name");
                jobSkillMap.computeIfAbsent(job, k -> new ArrayList<>()).add(skill.toLowerCase());
            }

            // User skills array
            String[] userSkillsArray = userSkills.toLowerCase().split("[, ]+");

            for (String job : jobSkillMap.keySet()) {
                List<String> requiredSkills = jobSkillMap.get(job);

                int matchedSkills = 0;
                for (String uSkill : userSkillsArray) {
                    for (String rSkill : requiredSkills) {
                        if (rSkill.contains(uSkill.trim())) {
                            matchedSkills++;
                            break; // avoid double counting same required skill
                        }
                    }
                }

                if (matchedSkills > 0) {
                    int percent = (matchedSkills * 100) / requiredSkills.size();
                    percent = Math.min(percent, 100); // clamp to 100%
                    matchedJobs.put(job, percent);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return matchedJobs;
    }
}
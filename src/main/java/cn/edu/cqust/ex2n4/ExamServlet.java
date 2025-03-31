package cn.edu.cqust.ex2n4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

@WebServlet(name = "ExamServlet",value = "/ExamServlet")
public class ExamServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        int score = 0;

        String q1 = request.getParameter("q1");
        if ("Microsoft公司".equals(q1)) {
            score += 10;
        }

        String[] q2Answers = request.getParameterValues("q2");
        if (q2Answers != null) {
            Set<String> correctAnswers = new HashSet<>(Arrays.asList("C++", "Java", "Python"));
            boolean hasError = false;
            int correctCount = 0;

            for (String ans : q2Answers) {
                if (correctAnswers.contains(ans)) {
                    correctCount++;
                } else {
                    hasError = true;
                    break;
                }
            }

            if (!hasError) {
                if (correctCount == 3) {
                    score += 10;
                } else if (correctCount > 0) {
                    score += 5;
                }
            }
        }

        String q3 = request.getParameter("q3");
        if (q3 != null && q3.trim().equals("北京")) {
            score += 10;
        }

        out.println("<html><body>");
        out.println("<h2>你的分数：" + score + "分</h2>");
        out.println("</body></html>");
    }
}

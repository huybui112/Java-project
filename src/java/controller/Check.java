/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BlogDAO;
import dao.CourseDAO;
import dao.blogQuestionDAO;
import dao.VocabularyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Blog;
import model.Course;
import model.User;
import model.Vocabulary;
import model.blogQuestion;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Check", urlPatterns = {"/check"})
public class Check extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if(user == null)
        {
            response.sendRedirect("login.jsp");
            return;
        }
        response.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if(user == null)
        {
            response.sendRedirect("login.jsp");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        int cnt = Integer.parseInt(request.getParameter("cnt"));
        blogQuestionDAO blogquestionDAO = new blogQuestionDAO();
        BlogDAO blogDAO = new BlogDAO();
        Blog d = blogDAO.findBlogByIdCourse(id);
        ArrayList<blogQuestion> b = blogquestionDAO.layBlogQuestionBangIdb(d.getId());
        ArrayList<String> tmp = new ArrayList<>();
        ArrayList<String> kq = new ArrayList<>();
        for(int i=1; i< cnt; i++){
            String ans = request.getParameter("ans"+i);
            if(ans.trim().equals(b.get(i-1).getAnswer().trim().toLowerCase())){
                kq.add("Correct");
            }
            else{
                kq.add("Incorrect");
            }
            tmp.add(ans);
            
        }
        CourseDAO courseDao = new CourseDAO();
        Course a = courseDao.timKhoaHocBangId(id);
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        ArrayList<Vocabulary> c = vocabularyDAO.timTatCaTuMoiBangCourse(id);
        request.setAttribute("a", a);
        request.setAttribute("b", b);
        request.setAttribute("c", c);
        request.setAttribute("d", d);
        request.setAttribute("tmp", tmp);
        request.setAttribute("kq", kq);
        request.getRequestDispatcher("check.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

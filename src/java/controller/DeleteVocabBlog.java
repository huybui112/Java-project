/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BlogDAO;
import dao.CourseDAO;
import dao.DictionaryDAO;
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
import model.Dictionary;
import model.User;
import model.Vocabulary;
import model.blogQuestion;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "DeleteVocabBlog", urlPatterns = {"/deletevocabblog"})
public class DeleteVocabBlog extends HttpServlet {
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int vid = Integer.parseInt(request.getParameter("vid"));
        int id = Integer.parseInt(request.getParameter("id"));
        CourseDAO courseDao = new CourseDAO();
        BlogDAO blogDAO = new BlogDAO();
        blogQuestionDAO blogquestionDAO = new blogQuestionDAO();
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        vocabularyDAO.xoaTuMoiBangId(vid);
        Course a = courseDao.timKhoaHocBangId(id);
        ArrayList<Vocabulary> c = vocabularyDAO.timTatCaTuMoiBangCourse(id);
        Blog d = blogDAO.findBlogByIdCourse(a.getId());
        ArrayList<blogQuestion> b = blogquestionDAO.layBlogQuestionBangIdb(d.getId());
        request.setAttribute("a", a);
        request.setAttribute("b", b);
        request.setAttribute("c", c);
        request.setAttribute("d", d);
        request.getRequestDispatcher("editblog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

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
@WebServlet(name = "AddVocabBlog", urlPatterns = {"/addvocabblog"})
public class AddVocabBlog extends HttpServlet {
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action.equals("1"))
        {
            themTuMoi1(request, response);
        }
        else
        {
            themTuMoi2(request, response);
        }
    }
    
    private void themTuMoi1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        String word = request.getParameter("vocab").trim();
        CourseDAO courseDao = new CourseDAO();
        DictionaryDAO dictDAO = new DictionaryDAO();
        BlogDAO blogDAO = new BlogDAO();
        blogQuestionDAO blogquestionDAO = new blogQuestionDAO();
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        Dictionary t = dictDAO.layDictionaryBangWord(word.toLowerCase());
        if(t == null)
            request.setAttribute("error_vocab", "Từ không có sẵn trong hệ thống");
        else
            vocabularyDAO.taoTuMoi(t.getWord(), t.getMean(), t.getPronunciation(), t.getPartOfSpeech(), id);
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
    
        private void themTuMoi2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int id = Integer.parseInt(request.getParameter("id"));
        String word = request.getParameter("word").trim();
        String pronunciation = request.getParameter("pronunciation").trim();
        String partOfSpeech = request.getParameter("partOfSpeech").trim();
        String mean = request.getParameter("mean").trim();
        String syn = request.getParameter("syn").trim();
        String anton = request.getParameter("anton").trim();
        CourseDAO courseDao = new CourseDAO();
        DictionaryDAO dictDAO = new DictionaryDAO();
        BlogDAO blogDAO = new BlogDAO();
        blogQuestionDAO blogquestionDAO = new blogQuestionDAO();
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        if(!dictDAO.taoTuMoi(word, mean, pronunciation, partOfSpeech, syn, anton))
            request.setAttribute("error_vocab", "Từ đã có sẵn trong hệ thống! Vui lòng thử lại!");
        else
        {
            vocabularyDAO.taoTuMoi(word, mean, pronunciation, partOfSpeech, id);
        }
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
    public String getServletInfo() {
        return "Short description";
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BlogDAO;
import dao.CourseDAO;
import dao.DictionaryDAO;
import dao.VocabularyDAO;
import dao.blogQuestionDAO;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Dictionary;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "createBlog", urlPatterns = {"/createBlog"})
public class createBlog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
        } else if (user.getUsername().equals("user1")) {
            response.sendRedirect("themblog.jsp");
        } else {
            response.sendRedirect("list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        DictionaryDAO dictionaryDAO = new DictionaryDAO();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String title = (String) request.getParameter("title");
        String essay = (String) request.getParameter("essay");
        int cnt = Integer.parseInt(request.getParameter("inputCount"));
        int cntTu = Integer.parseInt(request.getParameter("inputCountTu"));
        int cntTuMoi = 0;
        try {
            cntTuMoi = Integer.parseInt(request.getParameter("inputCountTuMoi"));
        } catch (Exception e) {
            System.out.println(e);
        }
        boolean checkError = false;
        String message = "Từ bạn thêm không có sẵn trong từ điển: ";
        for (int i = 0; i < cntTu; i++) {
            String word = request.getParameter("vocab" + i).trim();
            if(word.equalsIgnoreCase("")){
                continue;
            }
            Dictionary t = dictionaryDAO.layDictionaryBangWord(word.toLowerCase());
            if (t == null) {
                if (checkError == false) {
                    message += word;
                    checkError = true;
                } else {
                    message += ", " + word;
                }
            }

        }
        if (checkError) {
            request.setAttribute("message", message);
            request.setAttribute("title", title);
            request.setAttribute("essay", essay);
            request.getRequestDispatcher("themblog.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        BlogDAO blogDAO = new BlogDAO();
        blogQuestionDAO blogquestionDAO = new blogQuestionDAO();
        CourseDAO courseDao = new CourseDAO();
        Date date = new java.util.Date();
        courseDao.taoKhoaHoc(title, title, date);
        int idc = courseDao.findCourseByName(title).getId();
        blogDAO.taoBlog(essay, idc);
        int idb = blogDAO.findBlogByIdCourse(idc).getId();
        for (int i = 0; i <= cnt; i++) {
            String question = request.getParameter("questions" + i);
            String a = request.getParameter("A" + i);
            String b = request.getParameter("B" + i);
            String c = request.getParameter("C" + i);
            String d = request.getParameter("D" + i);
            String answer = request.getParameter("answer" + i);
            blogquestionDAO.taoQuestionBlog(question, a, b, c, d, answer, idb);
        }
        for (int i = 0; i < cntTuMoi; i++) {
            String word = request.getParameter("word" + i).trim();
            if(word.equalsIgnoreCase("")){
                continue;
            }
            String mean = request.getParameter("mean" + i).trim();
            String pronunciation = request.getParameter("pronunciation" + i).trim();
            String partOfSpeech = request.getParameter("partOfSpeech" + i).trim();
            vocabularyDAO.taoTuMoi(word, mean, mean, title, idc);
            dictionaryDAO.taoTuMoi(word, mean, pronunciation, partOfSpeech);
        }
        
        for (int i = 0; i < cntTu; i++) {
            
            String word = request.getParameter("vocab" + i).trim();
            if(word.equalsIgnoreCase("")){
                continue;
            }
            Dictionary t = dictionaryDAO.layDictionaryBangWord(word.toLowerCase());
            vocabularyDAO.taoTuMoi(t.getWord(), t.getMean(), t.getPronunciation(), t.getPartOfSpeech(), idc);
        }
        response.sendRedirect("list");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

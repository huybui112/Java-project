/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CourseDAO;
import dao.ProcessDAO;
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
import model.QuestionVoca;
import model.User;
import model.Process;
import model.Vocabulary;

/**
 *
 * @author nhanv
 */
@WebServlet(name = "KiemTraDapAnServlet", urlPatterns = {"/kiemtra"})
public class KiemTraDapAnServlet extends HttpServlet {
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
        int stt = Integer.parseInt(request.getParameter("stt"));
        stt++;
        String selectedValue = (String) request.getParameter("selectedValue");
        String ans = (String) request.getParameter("ans");
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
        ArrayList<QuestionVoca> list = (ArrayList<QuestionVoca>) session.getAttribute("listQuestion");
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        int idv = Integer.parseInt(request.getParameter("idv"));
        int point = Integer.parseInt(request.getParameter("point"));
        String mess = null;
        if (selectedValue.trim().equalsIgnoreCase(ans.trim())) {
            Vocabulary voca = vocabularyDAO.timTuMoiBangId(idv);
            int idu = user.getId();
            ProcessDAO processDAO = new ProcessDAO();
            Process process = processDAO.findProcess(idu, voca.getId());
            if (process == null) {
                boolean taoProcess = processDAO.taoProcess(idu, voca.getId());
                process = processDAO.findProcess(idu, voca.getId());
            }

            boolean kt = processDAO.updateProcess(idu, voca.getId());

            mess = "Đúng";
            point++;
        } else {
            mess = "Sai";
        }
        request.setAttribute("stt", stt);
        request.setAttribute("mess", mess);
        request.setAttribute("idv", idv);
        request.setAttribute("point", point);
        request.getRequestDispatcher("kiemtradapan.jsp").forward(request, response);
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

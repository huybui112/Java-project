/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import model.User;
import model.Process;
import model.Vocabulary;

/**
 *
 * @author nhanv
 */
@WebServlet(name = "FlashCardServlet", urlPatterns = {"/flashcard"})
public class FlashCardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idv = Integer.parseInt(request.getParameter("idv"));
        int idc = Integer.parseInt(request.getParameter("idc"));
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user==null){
            response.sendRedirect("home");
            return;
        }
        VocabularyDAO vocabularyDAO = new VocabularyDAO();
        ArrayList<Vocabulary> list = vocabularyDAO.timTatCaTuMoiBangCourse(idc);
        
        if (idv < 0 || idv >= list.size()) {
            response.sendRedirect("list");
        } else {
            
            Vocabulary voca = list.get(idv);
            int idu = user.getId();
            ProcessDAO processDAO = new ProcessDAO();
            Process process = processDAO.findProcess(idu, voca.getId());
            if(process==null){
                boolean taoProcess=processDAO.taoProcess(idu, voca.getId());
                process = processDAO.findProcess(idu, voca.getId());
            }
            if(process.getProcess()==0){
                boolean kt = processDAO.updateProcess(idu, voca.getId());
            }
            
            request.setAttribute("idv", idv);
            request.setAttribute("idc", idc);
            request.setAttribute("size",list.size());
            request.setAttribute("voca", voca);
            request.getRequestDispatcher("flashcard.jsp").forward(request, response);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

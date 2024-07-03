/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.DictionaryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author ASUS
 */
@WebServlet(name="editDictionary", urlPatterns={"/editDictionary"})
public class editDictionary extends HttpServlet {
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
        else if(!user.getUsername().equals("user1"))
        {
            response.sendRedirect("list");
            return;
        }
        String action = request.getParameter("action");
        if(action.equals("add"))
        {
            request.getRequestDispatcher("/editDictionary.jsp").forward(request, response);
        }
        else if(action.equals("edit"))
        {
            request.getRequestDispatcher("/editDictionary.jsp").forward(request, response);
        }
        else
        {
            int wordId = Integer.parseInt(request.getParameter("wordId"));
            DictionaryDAO dictionaryDAO = new DictionaryDAO();
            dictionaryDAO.xoaTuBangId(wordId);
            response.sendRedirect("dict");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("add"))
        {
            themTuVung(request, response);
        }
        else
        {
            suaTuVung(request, response);
        }
    }

    private void themTuVung(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        String word = request.getParameter("word");
        String pronunciation = request.getParameter("pronunciation");
        String partOfSpeech = request.getParameter("partOfSpeech");
        String mean = request.getParameter("mean");
        String syn = request.getParameter("syn");
        String anton = request.getParameter("anton");
        if(syn == null)
            syn = " ";
        if(anton == null)
            anton = " ";
        DictionaryDAO dictionaryDAO = new DictionaryDAO();
        if(dictionaryDAO.taoTuMoi(word, mean, pronunciation, partOfSpeech,syn, anton))
        {
            response.sendRedirect("./editDictionary?action=add");
        }
        else
        {
            request.setAttribute("error", "Từ tồn tại trong từ điển. Hãy thử lại!");
            request.getRequestDispatcher("/editDictionary.jsp").forward(request, response);
        }
    }
    
    private void suaTuVung(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        int id = Integer.parseInt(request.getParameter("wordId"));
        String word = request.getParameter("word");
        String pronunciation = request.getParameter("pronunciation");
        String partOfSpeech = request.getParameter("partOfSpeech");
        String mean = request.getParameter("mean");
        String syn = request.getParameter("syn");
        String anton = request.getParameter("anton");
        if(syn == null)
            syn = " ";
        if(anton == null)
            anton = " ";
        DictionaryDAO dictionaryDAO = new DictionaryDAO();
        if(dictionaryDAO.suaTuVungBangId(id, word, mean, pronunciation, partOfSpeech,syn, anton))
        {
            response.sendRedirect("dict");
        }
        else
        {
            request.setAttribute("error", "Sửa từ thất bại! Hãy từ lại!");
            request.getRequestDispatcher("/editDictionary.jsp?wordId="+id).forward(request, response);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

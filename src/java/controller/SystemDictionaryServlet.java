/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.DictionaryDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
 * @author ASUS
 */
@WebServlet(name="SystemDictionaryServlet", urlPatterns={"/dict"})
public class SystemDictionaryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if(user == null)
        {
            response.sendRedirect("login.jsp");
            return;
        }
        DictionaryDAO dictionaryDao = new DictionaryDAO();
        ArrayList<Dictionary> dict = null;
        String words = request.getParameter("words");
        int page = 0;
        int maxPage = 0;
        try{
            page = Integer.parseInt(request.getParameter("page"));
        }
        catch(NumberFormatException e)
        {
            System.out.println(e);
        }
        if(words == null)
        {
            dict = dictionaryDao.topTenWord(page*10, "");
            maxPage = dictionaryDao.getMaxPage("");
        }
        else
        {
            dict = dictionaryDao.topTenWord(page*10, words.trim());
            maxPage = dictionaryDao.getMaxPage(words.trim());
        }
        request.setAttribute("dict", dict);
        request.setAttribute("page", page);
        request.setAttribute("words", words);
        request.setAttribute("maxPage", maxPage);
        RequestDispatcher rd = request.getServletContext().getRequestDispatcher("/tudien.jsp");
        rd.forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

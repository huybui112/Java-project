/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.UserDAO;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import model.User;

/**
 *
 * @author ASUS
 */
@WebServlet(name="LoginProcessServlet", urlPatterns={"/login"})
public class LoginProcessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserDAO userDao = new UserDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String url = null;
        
        User user = userDao.timUser(username, password);
        if(user!= null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            url = "list";
//            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
//            rd.forward(request, response);
            response.sendRedirect(url);
        }
        else
        {
            url = "/login.jsp";
            String error_mess = "ĐĂNG NHẬP THẤT BẠI!";
            request.setAttribute("error_mess", error_mess);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
//            response.sendRedirect(url);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

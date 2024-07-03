package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import xtension.SendEmail;

/**
 *
 * @author ASUS
 */
@WebServlet(urlPatterns={"/register"})
public class RegisterProcessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("register.jsp");
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        UserDAO userDao = new UserDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String previousUrl = "register";
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.setAttribute("email", email);
        if(action != null)
        {
            String systemCode = request.getParameter("systemCode");
            String userCode = request.getParameter("code");
            String time = request.getParameter("timeToVertify");
            LocalDateTime timeToVertify = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            LocalDateTime now = LocalDateTime.now();
            if(systemCode.equals(userCode) && now.isBefore(timeToVertify))
            {
               userDao.taoUser(username, password, email);
               response.sendRedirect("./login.jsp");
            }
            else if(!systemCode.equals(userCode))
            {
                String error_mess = "NHẬP SAI MÃ CODE!";
                request.setAttribute("error_mess", error_mess);
                request.setAttribute("systemCode", systemCode);
                request.setAttribute("timeToVertify", time);
                request.setAttribute("previousUrl", previousUrl);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/xacThuc.jsp");
                rd.forward(request, response);                
            }
            else
            {
                String error_mess = "THỜI GIAN XÁC THỰC ĐÃ HẾT VUI LÒNG ĐĂNG KÍ LẠI!";
                boolean timeOutVertify = true;
                request.setAttribute("error_mess", error_mess);
                request.setAttribute("timeOutVertify", timeOutVertify);
                request.setAttribute("previousUrl", previousUrl);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/xacThuc.jsp");
                rd.forward(request, response);     
            }
        }
        else
        {
            if(userDao.kiemTraTonTai1(username, email))
            {
                SendEmail se = new SendEmail();
                String code = "";
                String timeToVertify = LocalDateTime.now().plusMinutes(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                Random rad = new Random();
                for(int i = 0; i < 6; i++)
                    code+=rad.nextInt(10);
                System.out.println(code);
                se.sendVertification(email, code);
                request.setAttribute("systemCode", code);
                request.setAttribute("timeToVertify", timeToVertify);
                request.setAttribute("previousUrl", previousUrl);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/xacThuc.jsp");
                rd.forward(request, response);
            }
            else
            {
                String error_mess = "ĐĂNG KÝ THẤT BẠI!";
                request.setAttribute("error_mess", error_mess);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

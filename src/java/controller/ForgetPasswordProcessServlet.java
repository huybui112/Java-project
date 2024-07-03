/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import xtension.SendEmail;

/**
 *
 * @author ASUS
 */
@WebServlet(name="ForgetPasswordProcessServlet", urlPatterns={"/forget-password"})
public class ForgetPasswordProcessServlet extends HttpServlet {
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
        response.sendRedirect("forget-password.jsp");
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getParameter("action");
        String previousUrl = "forget-password";
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        request.setAttribute("username", username);
        request.setAttribute("email", email);
        UserDAO userDao = new UserDAO();
        if(action == null)
        {
            if(userDao.kiemTraTonTai2(username, email))
            {
                SendEmail se = new SendEmail();
                String code = "";
                Random rad = new Random();
                String timeToVertify = LocalDateTime.now().plusMinutes(1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
                for(int i = 0; i < 6; i++)
                {
                    code+=rad.nextInt(10);
                }
                se.sendVertification(email, code);
                request.setAttribute("systemCode", code);
                request.setAttribute("timeToVertify", timeToVertify);
                request.setAttribute("previousUrl", previousUrl);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/xacThuc.jsp");
                rd.forward(request, response);
            }
            else
            {
                String error_mess = "SAI TÀI KHOẢN HOẶC EMAIL, VUI LÒNG THỬ LẠI!";
                request.setAttribute("error_mess", error_mess);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/forget-password.jsp");
                rd.forward(request, response);
            }
        }
        else if(action.equals("xac-thuc"))
        {
            String userCode = request.getParameter("code");
            String systemCode = request.getParameter("systemCode");
            String time = request.getParameter("timeToVertify");
            LocalDateTime timeToVertify = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            LocalDateTime now = LocalDateTime.now();
            if(userCode.equals(systemCode) && now.isBefore(timeToVertify))
            {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/change-password.jsp");
                rd.forward(request, response);
            }
            else if(!userCode.equals(systemCode))
            {
                String error_mess = "NHẬP SAI MÃ CODE!";
                request.setAttribute("timeToVertify", time);
                request.setAttribute("systemCode", systemCode);
                request.setAttribute("previousUrl", previousUrl);
                request.setAttribute("error_mess", error_mess);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/xacThuc.jsp");
                rd.forward(request, response);  
            }
            else
            {
                String error_mess = "THỜI GIAN XÁC THỰC ĐÃ HẾT VUI LÒNG THỬ LẠI!";
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
            User user = userDao.timUserBangEmail(email);
            String password1 = request.getParameter("password1");
            String password2 = request.getParameter("password2");
            if(password1.equals(password2) && !password1.equals(user.getPass()))
            {
                userDao.capNhatUser(user.getId(), password1, email);
                response.sendRedirect("./login.jsp");
            }
            else
            {
                String error_mess = "";
                if(!password1.equals(password2))
                    error_mess = "MẬT KHẨU KHÔNG GIỐNG NHAU VUI LÒNG THỬ LẠI!";
                else
                    error_mess = "MẬT KHẨU MỚI KHÔNG ĐƯỢC TRÙNG VỚI MẬT KHẨU CŨ";
                request.setAttribute("error_mess", error_mess);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/change-password.jsp");
                rd.forward(request, response);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

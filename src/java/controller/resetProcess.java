/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ProcessDAO;
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
@WebServlet(name="resetProcess", urlPatterns={"/resetProcess"})
public class resetProcess extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {  
        int idc = Integer.parseInt(request.getParameter("idc"));
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        if(user == null)
        {
            response.sendRedirect("login.jsp");
            return;
        }   
        int idu = user.getId();
        ProcessDAO processDAO = new ProcessDAO();
        processDAO.resetProcess(idu, idc);
        response.sendRedirect("list");
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

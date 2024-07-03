/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Course;
import model.User;

/**
 *
 * @author nhanv
 */
@WebServlet(name = "DanhSachKhoaHocServlet", urlPatterns = {"/list"})
public class DanhSachKhoaHocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user == null){
            response.sendRedirect("login.jsp");
            return;
        }
        CourseDAO courseDao = new CourseDAO();
        int page, maxPage = courseDao.getAllNumCourse();
        String filter = request.getParameter("filter");
        if(filter == null)
            filter = "all";
        try
        {
            page = Integer.parseInt(request.getParameter("page"));
        }
        catch(NumberFormatException e)
        {
            page = 0;
            System.out.println(e);
        }
        ArrayList<Course> listCourse = courseDao.getFirstFiveCourse(page, filter);
        request.setAttribute("listCourse", listCourse);
        request.setAttribute("page", page);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("filter", filter);
        
        request.getRequestDispatcher("danhsachkh.jsp").forward(request, response);
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

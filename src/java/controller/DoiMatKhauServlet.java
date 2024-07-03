/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.UserDAO;
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
 * @author nhanv
 */
@WebServlet(name="DoiMatKhauServlet", urlPatterns={"/doimatkhau"})
public class DoiMatKhauServlet extends HttpServlet {
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
        String matKhauHienTai =request.getParameter("currentPassword");
        String matKhauMoi =request.getParameter("newPassword");
        String matKhauXacThuc =request.getParameter("confirmPassword");
        if(!matKhauHienTai.equals(user.getPass())){
            String error = "Mật khẩu không chính xác!";
            request.setAttribute("error", error);
        }else if(!matKhauMoi.equals(matKhauXacThuc)){
            String error = "Mật khẩu xác nhận khác mật khẩu mới!";
            request.setAttribute("error", error);
        }else{
            UserDAO userDAO = new UserDAO();
            boolean kt = userDAO.capNhatUser(user.getId(), matKhauMoi, user.getEmail());
            String error = null;
            if(!kt){
                error ="Thay đổi mật khẩu thất bại!";
            }else{
                error = "Thay đổi mật khẩu thành công!";
                
                User newUser = new User();
                newUser.setId(user.getId());
                newUser.setUsername(user.getUsername());
                newUser.setEmail(user.getEmail());
                newUser.setPass(matKhauMoi);
                session.removeAttribute("user");
                session.setAttribute("user", newUser);
                
                
            }
            request.setAttribute("error", error);
        }
        request.setAttribute("matKhauHienTai", matKhauHienTai);
        request.setAttribute("matKhauMoi", matKhauMoi);
        request.setAttribute("matKhauXacThuc", matKhauXacThuc);
        request.getRequestDispatcher("doimatkhau.jsp").forward(request, response);
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

<%-- 
    Document   : register.jsp
    Created on : Nov 4, 2023, 11:55:01 PM
    Author     : ASUS
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng Ký</title>
        <link rel="stylesheet" href="./static/register.css">
    </head>
    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="home" style="font-size: 30px">Trang Chủ</a>
            </div>
        </header>
        <main class="main-content">
            <%
                User user = (User) session.getAttribute("user");
                String error_mess = (String) request.getAttribute("error_mess");
                String username = (String) request.getAttribute("username");
                String password = (String) request.getAttribute("password");
                String email = (String) request.getAttribute("email");

            %>
            <h1>ĐĂNG KÝ VÀ BẮT ĐẦU HỌC</h1>
            <form action="register" method="post">
                <label for="name" class="lef-align">Tên người dùng:</label>
                <input type="text" id="name" name="username" style="padding: 15px;" value="<%= (username != null) ? username : "" %>"required>

                <label for="password" class="lef-align">Mật khẩu:</label>
                <input type="password" id="password" style="padding: 15px;" name="password" value="<%= (password != null) ? password : "" %>" required>
                <label for="email" class="lef-align">Email:</label>
                <input type="email" id="email" name="email"  value="<%= (email != null) ? email : "" %>" required>
                <input type="submit" value="ĐĂNG KÝ">
                <%
                    if (user == null) {
                        if (error_mess != null) {%>
                <h6 style="color: red; font-size: 20px; margin:18px;"><%=error_mess%></h6>
                <%}
                    if (username == null) {
                        username = "";
                    }
                    if (password == null) {
                        password = "";
                        email = "";
                    }
                } else {%>
                <jsp:forward page="list?role=2"/>
                <%}
                %>
            </form>
        </main>
        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>
</html>
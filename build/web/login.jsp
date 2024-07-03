<%-- 
    Document   : login.jsp
    Created on : Nov 4, 2023, 10:47:47 PM
    Author     : ASUS
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="./static/login.css">

    </head>
    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="home"; style="font-size: 30px">Trang Chủ</a>
            </div>
        </header>
        <main class="main-content">
            <%
                User user = (User) session.getAttribute("user");
                String error_mess = (String) request.getAttribute("error_mess");
                String username = (String) request.getAttribute("username");
                String password = (String) request.getAttribute("password");
            %>
            <h1>ĐĂNG NHẬP ĐỂ BẮT ĐẦU HỌC</h1>
            <form method="post" action="login">
                <label for="username" class="lef-align">Tên người dùng:</label>
                <input class="input-field" type="text" id="username" name="username" value="<%= (username != null) ? username : ""%>" required>

                <label for="password" class="lef-align">Mật khẩu:</label>
                <input class="input-field" type="password" id="password" name="password" value="<%= (password != null) ? password : ""%>" required>


                <input type="submit" value="ĐĂNG NHẬP" >   
                <%
                    if (user == null) {
                        if (error_mess != null) {%>
                <h6 id="error-message" style="color: red; font-size: 20px; margin:18px;"><%=error_mess%></h6>
                <%}
                    if (username == null) {
                        username = "";
                    }
                    if (password == null) {
                        password = "";
                    }
                } else {%>
                <jsp:forward page="list?role=2"/>
                <%}
                %>
                <div class="form-link">
                    <a href="forget-password.jsp" style="font-size: 20px">Quên mật khẩu</a>
                </div>
                <div class="form-link">
                    <a href="register.jsp" style="font-size: 20px">Đăng ký</a>
                </div>


            </form>
            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var errorMessage = document.getElementById('error-message');
                    var inputFields = document.querySelectorAll('.input-field');

                    inputFields.forEach(function (input) {
                        input.addEventListener('click', function () {
                            errorMessage.style.display = 'none';
                        });
                    });

                    inputFields.forEach(function (input) {
                        input.addEventListener('focus', function () {
                            errorMessage.style.display = 'none';
                        });
                    });
                });
            </script>
        </main>
        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->

            </div>
        </footer>

    </body>
</html>
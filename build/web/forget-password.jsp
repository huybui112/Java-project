<%-- 
    Document   : forget-password
    Created on : Nov 11, 2023, 10:15:42 AM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
        <link rel="stylesheet" href="./static/forgetcss.css">
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                var errorMess = document.querySelector('.error');

                // Ẩn thẻ h3.error khi ô input nhận sự kiện focus
                document.querySelectorAll('input[type="text"], input[type="email"]').forEach(function(input) {
                    input.addEventListener('focus', function() {
                        errorMess.style.display = 'none';
                    });
                });
            });
        </script>
    </head>
    <body>
        
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="home.jsp" >Trang Chủ</a>
            </div>
        </header>

        <form action="forget-password" method="post">
            <h3>Nhập thông tin cá nhân</h3>
            <div>
                <label for="usernameId" class="lef-align">Username:</label>
                <input name="username" type="text" id="usernameId" required="true">
            </div>
            <div>
                <label for="email" class="lef-align">Email:</label>
                <input name="email" type="email" id="emailId" required="true">
            </div>
            <%
                String error_mess = (String) request.getAttribute("error_mess");
                if (error_mess != null) {%>
                    <h3 class="error" style="color: red;"><%=error_mess%></h3>
            <%}
            %>
            <input type="submit" value="SEND">
        </form>
    </body>
</html>
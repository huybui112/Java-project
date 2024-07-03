<%-- 
    Document   : change-password
    Created on : Nov 11, 2023, 2:46:52 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link rel="stylesheet" href="./static/changecss.css">
    </head>
    <body>
        <%
            String username = (String) request.getAttribute("username");
            String email = (String) request.getAttribute("email");
            String action = "change-password";
            String error_mess = (String) request.getAttribute("error_mess");
            if(error_mess != null)
            {%>
                <h3 style="color: red;"><%=error_mess%></h3>
            <%}
        %>
        
        
        <form action="forget-password" method="post">
            <h3>Tạo mật khẩu mới</h3>
            <div>
                <label for="password1Id" class="lef-align">Password:</label>
                <input type="password" name="password1" id="password1Id">
            </div>
            <div>
                <label for="password2Id" class="lef-align">Password again:</label>
                <input type="password" name="password2" id="password2Id">
            </div>
            <input type="hidden" name="username" value="<%=username%>">
            <input type="hidden" name="email" value="<%=email%>">
            <input type="hidden" name="action" value="<%=action%>">
            <input type="submit" value="CHANGE">
        </form>
    </body>
</html>

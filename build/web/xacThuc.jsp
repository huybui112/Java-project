<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xác Thực</title>
        <link rel="stylesheet" href="./static/xacthuc.css">
    </head>
    <body>
        <div class="container">
            <%
                String error_mess = (String) request.getAttribute("error_mess");
                String username = (String) request.getAttribute("username");
                String password = (String) request.getAttribute("password");
                String email = (String) request.getAttribute("email");
                String systemCode = (String) request.getAttribute("systemCode");
                String timeToVertify = (String) request.getAttribute("timeToVertify");
                Boolean timeOutVertify = (Boolean) request.getAttribute("timeOutVertify");
                String previousUrl = (String) request.getAttribute("previousUrl");

            %>
            <form action="<%=previousUrl%>" method="post">
                <h3 style="font-size: 2em">NHẬP MÃ XÁC THỰC</h3>
                <label for="codeId" class="lef-align" >Code:</label>
                <input type="text" name="code" id="codeId">
                <input type="hidden" name="action" value="xac-thuc">
                <input type="hidden" name="username" value="<%=username%>">
                <input type="hidden" name="password" value="<%=password%>">
                <input type="hidden" name="email" value="<%=email%>">
                <input type="hidden" name="timeToVertify" value="<%=timeToVertify%>">
                <input type="hidden" name="systemCode" value="<%=systemCode%>">
                <%
                    if (error_mess != null) {%>
                <p style="color: red; font-size: 20px;">*<%=error_mess%></p>
                <%}
                %>
                <div class="button-row">
                    <input type="submit" value="XÁC THỰC" class="button-xac-thuc" style="background-color: goldenrod; font-weight: bold">
                    <a href="<%=previousUrl%>.jsp" style="font-size: 20px" class =Back-link>QUAY LẠI</a>
                </div>
            </form>
        </div>
    </body>
</html>
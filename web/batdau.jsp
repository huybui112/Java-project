<%-- 
    Document   : batdau
    Created on : Nov 13, 2023, 12:38:55 PM
    Author     : ADMIN
--%>

<%--<%@page import="model.Vocabulary"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         
        <a href = "ontap?idc=${idc}&tu1=${tu1}&idv=0&ma=${ma}">Bắt đầu</a>
        
    </body>
</html>-->
<%-- 
    Document   : batdau
    Created on : Nov 13, 2023, 12:38:55 PM
    Author     : ADMIN
--%>

<%@page import="model.User"%>
<%@page import="model.Vocabulary"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="static/danhsachkh.css">
        <title>Trang chủ</title>
    </head>

    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="home">Trang Chủ</a>

                <a class="item " href="list?role=2">Danh Sách Khóa Học </a>


                <%
                    User user = (User) session.getAttribute("user");
                    if (user != null) {%>
                <div class="item right">
                    <a id="info " href="#">Thông Tin </a>
                    <a id="logout " href="LogoutProcessServlet">Đăng Xuất</a>
                </div>
                <%}
                %>

            </div>
        </header>
        <main class="main-content">
            <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
            %>
            <p><%=error%></p>

            <%
            } else {
            %>
            <form action="ontap?stt=${stt}">
                
                <a type="submit">Bat dau</a>
            </form>
            
            <%
                }
            %>
        </main>



        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>

</html>

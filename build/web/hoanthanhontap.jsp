<%-- 
    Document   : hoanthanhontap
    Created on : Nov 15, 2023, 3:58:53 PM
    Author     : nhanv
--%>

<%@page import="model.QuestionVoca"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="model.Vocabulary"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" style="height: 100%">

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

                <a class="item " href="list">Danh Sách Khóa Học </a>


                <%
                    User user = (User) session.getAttribute("user");
                    ArrayList<QuestionVoca> list =(ArrayList<QuestionVoca>) session.getAttribute("listQuestion");
                    session.removeAttribute("listQuestion");
                    if (user != null) {%>
                <div class="item right">
                    <a id="info " href="thongtinnguoidung.jsp">Thông Tin </a>
                    <a id="logout " href="LogoutProcessServlet">Đăng Xuất</a>
                </div>
                <%}
                %>

            </div>
        </header>
        <main class="main-content">
            <h2 style="margin-left: 30px">${point}/<%=list.size()%></h2>
            <h3 style="text-align: center; font-size: 30px">Bạn đã hoàn thành ôn tập !</h3>
            <a href="list?role=2" style="text-decoration: none; position: absolute; top: 60px; right: 50px; font-size: 25px">Thoát</a>
        </main>


        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>

</html>
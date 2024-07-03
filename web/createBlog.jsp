<%-- 
    Document   : createBlog
    Created on : Nov 17, 2023, 9:20:05 PM
    Author     : ASUS
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm khóa học</title>
        <link rel="stylesheet" href="./static/themkhoahoc.css">
    </head>
    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="home">Trang Chủ</a>

                <a class="item " href="list">Danh Sách Khóa Học</a>

                <div class="item right">
                    <a id="info " href="#">Thông Tin </a>
                    <a id="logout " href="LogoutProcessServlet">Đăng Xuất</a>

                </div>


            </div>
        </header>
        <main class="main-content">
            <h1>Thêm khóa học </h1>
            <form class="form-group" action="createCourse" method="post">
                <%
                    String error_mess = (String) request.getAttribute("error_mess");
                    String nameCourse = (String) request.getAttribute("nameCourse");
                    String description = (String) request.getAttribute("description");
                    String content = (String) request.getAttribute("content");
                    if (error_mess != null) {
                %>
                <p style="color: red;"><%=error_mess%></p>
                <%
                } else {
                    nameCourse = "";
                    description = "";
                    String done = (String) request.getAttribute("done");
                    if (done != null) {
                %>
                <p style="color: aquamarine;"><%=done%></p>
                <%
                        }
                    }
                %>
                <label for="name">Tên khóa học:</label>
                <input type="text" id="name" maxlength="30" value="<%=nameCourse%>" name="username" required>

                <label for="description">Tiêu đề:</label>
                <textarea  type="text" id="description" maxlength="150" rows="4" cols="50" value="<%=description%>" name="description" required></textarea>
                
                <label for="contentId">Nội dung</label>
                <textarea type="text" id="contentId" maxlength="150" rows="10" cols="50" value="<%=content%>" name="content" required></textarea>

                <input type="submit" value="Tạo">
            </form>
                <div>
                    <a href="themkhoahoc.jsp">Create Course</a>
                </div>
        </main>
        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>
</html>
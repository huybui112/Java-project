<%-- 
    Document   : thongtinnguoidung
    Created on : Nov 11, 2023, 9:00:59 PM
    Author     : nhanv
--%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="static/danhsachkh.css">
        <title>User Information Form</title>
        <style>
            body {
                display: grid;
                gap: 0;
                margin: 0;
                grid-template-columns: repeat(5, 1fr);
                grid-template-rows: 411px 1fr 50px;
                grid-template-areas:
                    "header header header header header"
                    "content content content content content"
                    "footer footer footer footer footer";
            }

            form {
                max-width: 400px; /* Set a maximum width for the form */
                margin: 0 auto; /* Center the form within the main content */
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            }

            label {
                display: block;
                margin-bottom: 8px;
                font-weight: bold;
            }

            input {
                width: 100%;
                padding: 8px;
                margin-bottom: 16px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
            .fixed-header {
                grid-area: header;
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 40px;
                background-color: #333;
                color: #fff;
                z-index: 1000;
            }

            .header-content {
                display: grid;
                grid-template-columns: 1fr 60% 1fr;
            }

            .header-content .trang-chu, .header-content .item,.header-content .item right {
                font-size: 24px;
                text-decoration: none;
                display: flex;
                align-items: center;
                justify-content: center;
                color: aliceblue;
            }
            .item.right a {
                text-decoration: none; /* Remove underline for the Đăng Xuất link */

            }
            .header-content .trang-chu{
                font-size: 30px;
            }
            a {
                color: #007bff; /* Blue link color */
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline; /* Underline on hover */
            }

            /* Style the "Thay đổi mật khẩu" link */
            a.change-password-link {
                display: block;
                margin-top: 10px;
                color: #28a745; /* Green color */
            }

            a.change-password-link:hover {
                color: #218838; /* Dark green color on hover */
            }

            /* Style the content area to make it visually appealing */
            .main-content div {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            }
            button {
                background-color: #4caf50;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .main-content {
                grid-area: content;
                overflow-y: auto;
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: -136px; /* Sử dụng giá trị âm để đẩy nó lên trên */
            }

            .main-content div {
                background-color: #fff;
                width: 100%;
                max-width: 500px;
                padding: 50px;
                box-shadow: 0 4px 12px rgba(0, 0, 0, 0.3);
                text-align: center;
                border-radius: 10px;
                border: 2px solid #333; /*canthiet*/
            }


            button:hover {
                background-color: #45a049;
            }
            .trang-chu, .item,.item right
            {
                font-size: 20px;
                transition: transform 0.3s ease;
                display: inline-block;
            }

            .item right:hover,
            .item right:active,
            .item:hover,
            .item.active,
            .trang-chu:hover,
            .trang-chu:active
            {
                color: #ffe080;
                transform: scale(1.2);
            }
            footer {
                grid-area: footer;
                background-color: #333;
                color: #fff;
                display: flex;
                width: 100%;
            }
            .main-content label {
                display: block;
                margin-bottom: 16px;
                margin-top: 16px;
                font-weight: bold;
                color: #333; /* Set label text color */
                font-size: 20px;
            }

            .main-content input {
                width: 100%;
                padding: 10px;
                margin-bottom: 16px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
                
            }

            .main-content a {
                color: #007bff; /* Blue link color */
                text-decoration: none;
                margin-top: 20px;
            }

            .main-content a:hover {
                text-decoration: underline; /* Underline on hover */
            }

            /* Style the "Thay đổi mật khẩu" link */
            .main-content a.change-password-link {
                display: block;
                margin-top: 10px;
                color: #28a745; /* Green color */
            }

            .main-content a.change-password-link:hover {
                color: #218838; /* Dark green color on hover */
            }
        </style>
    </head>
    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="home">Trang Chủ</a>

                <a class="item " href="list?role=2">Danh Sách Blog </a>

                <%
                    User user = (User) session.getAttribute("user");
                    if (user != null) {%>
                <div class="item right">
                    <a id="logout " style="font-size: 24px" href="LogoutProcessServlet">Đăng Xuất</a>

                </div>
                <%}
                %>

            </div>
        </header>
        <main class="main-content">

            <div>
                <label for="username">Username:    <%=user.getUsername()%></label>



                <label for="email">Email:    <%=user.getEmail()%></label>

                <a href="doimatkhau.jsp" style="font-size: 20px">Thay đổi mật khẩu </a>

            </div>
        </main>
        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>

</html>

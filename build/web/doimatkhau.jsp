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
                margin-bottom: 16px;
                font-weight: bold;
                font-size: 20px;
            }

            input {
                width: 100%;
                padding: 15px;
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
            .main-content {
                margin-top: -267px; /* Sử dụng giá trị âm để đẩy nó lên trên */
            }

            button {
                background-color: #4caf50;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            button:hover {
                background-color: #45a049;
            }
            input[type="submit"] {
                background-color: goldenrod;
                color: #333;
                cursor: pointer;
                margin-top:16px;
                transition: background-color 0.3s ease, color 0.3s ease, box-shadow 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #FFD700; /* Một tông màu goldenrod khác để làm nổi bật hơn */
                color: #fff; /* Màu chữ trắng khi hover */
                box-shadow: 0 0 10px rgba(218, 165, 32, 0.5); /* Hiệu ứng shadow khi hover */
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
            h2 {
                text-align: center;
                text-transform: uppercase; /* Chuyển đổi chữ thành chữ in hoa */
                font-size: 24px;
                margin-bottom: 30px;
            }
        </style>
    </head>
    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="home">Trang Chủ</a>

                <a class="item " href="list">Danh Sách Khóa Học </a>

                <%
                    User user = (User) session.getAttribute("user");
                    if (user != null) {%>
                <div class="item right">
                    <a id="logout " href="LogoutProcessServlet" style="font-size: 24px;">Đăng Xuất</a>
                </div>
                <%}
                    String matKhauHienTai = (String) request.getAttribute("matKhauHienTai");
                    String matKhauMoi = (String) request.getAttribute("matKhauMoi");
                    String matKhauXacThuc = (String) request.getAttribute("matKhauXacThuc");
                    if (matKhauMoi == null) {
                        matKhauHienTai = "";
                        matKhauMoi = "";
                        matKhauXacThuc = "";
                    }
                %>

            </div>
        </header>
        <main class="main-content">
            <h2>${error}</h2>
            <div>
                <form action="doimatkhau" method="get">

                    <label for="currentPassword">Mật khẩu hiện tại:</label>
                    <input type="password" id="currentPassword" name="currentPassword" maxlength="50" required value="<%=matKhauHienTai%>">


                    <label for="newPassword">Mật khẩu mới:</label>
                    <input type="password" id="newPassword" name="newPassword" maxlength="50" required value="<%=matKhauMoi%>">


                    <label for="confirmPassword">Xác nhận lại mật khẩu mới:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" maxlength="50" required value="<%=matKhauXacThuc%>" >

                    <input type="submit" value="THAY ĐỔI">   
                </form>

            </div>
        </main>
        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>

</html>

<%-- 
    Document   : home.jsp
    Created on : Oct 31, 2023, 10:48:32 AM
    Author     : nhanv
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" style="height: 100%;">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./static/homecss.css">
        <title>Trang chủ</title>
    </head>
    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" >Trang Chủ</a>

                
                <%
                    User user = (User)session.getAttribute("user");
                    if(user != null)
                    {%>
                    
                    <a class="item " href="list">Danh Sách Blog </a>
                    <div class="item ">
                            <a id="info" style="font-size: 24px;" href="thongtinnguoidung.jsp">Thông Tin </a>
                            <a id="logout " style="font-size: 24px;" href="LogoutProcessServlet">Đăng Xuất</a>
                            
                    </div>

                <%}
                else
                {%>
                    <a class="item " > </a>
                    <div class="item ">
                            <a id="register" class="register1" style="font-size: 24px;" href="register">Đăng ký</a>
                            <a id="login" class="login1" style="font-size: 24px;" href="login">Đăng nhập</a>
                            
                    </div>
                <%}
                %>

            </div>
        </header>
        <main class="main-content">
             <div id="slider">
                <section class="hero_2021">
                    <div class="background" style="">
                        <div class="text">
                            <h1>
                                Học một ngoại ngữ để sử dụng trong đời thực
                            </h1>
                            <img class="lazy" data-original="https://f.hubspotusercontent20.net/hubfs/6968579/Memrise%20July%202020/Images/blue-flash.svg" alt="blue underline" src="https://6968579.fs1.hubspotusercontent-na1.net/hubfs/6968579/Memrise%20July%202020/Images/blue-flash.svg" style="display: inline;">
                            <p>
                                <span>Những mẫu câu hữu ích trong cuộc sống hàng ngày. <br>Được dạy với những video clip của người bản ngữ thực sự.</span>
                            </p>
                            
                        </div>
                        <div class="v3-girl-hero-image-tablet" style="background-image:url(https://6968579.fs1.hubspotusercontent-na1.net/hubfs/6968579/Home%20page%20top%20module%20images/hero_image_01.png)"></div>
                    </div>
                </section>
            </div>
        </main>
        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>
</html>


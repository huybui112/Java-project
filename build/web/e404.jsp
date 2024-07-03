<%-- 
    Document   : e404.jsp
    Created on : Nov 23, 2023, 4:59:33 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .image-container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh; /* Điều chỉnh chiều cao theo nhu cầu */
            }

            .image-container img {
                width: 100%;
                height: 100%;
             
            }
           
            .back-link {
                position: fixed;
                top: 20px;
                left: 20px;
                margin: 10px;
                display: inline-block;
                padding: 10px 20px;
                font-size: 16px;
                text-decoration: none;
                background-color: #ccc;
                color: #000;
                border: none;
                border-radius: 4px;
                
            }

            .back-link:hover {
                background-color: #aaa;
            }
        </style>
    </head>
    <body>
        <div class="image-container">
            <img src="./static/img/404_page_cover.jpg" alt="alt"/>
        </div>
        <a href="home" class="back-link">Back to home</a>
    </body>
</html>
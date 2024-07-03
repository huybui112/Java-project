<%-- 
    Document   : thongtinblog
    Created on : Nov 15, 2023, 10:47:23 PM
    Author     : ADMIN
--%>
<%@page import="dao.VocabularyDAO"%>
<%@page import="model.Vocabulary"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.blogQuestion"%>
<%@page import="model.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        body {
            /*            font-family: 'Arial', sans-serif;*/
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
            box-sizing: border-box;
            font-size: 16px;
            color: #333;
        }

        h1, h2, p {
            margin: 0;
            padding: 0;
        }

        h1 {
            font-size: 40px;
            text-align: center;
        }

        h2 {
            font-size: 30px;
        }

        p {
            font-size: 25px;
            text-align: left;
       
            margin-top:25px;
        }

        form {
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 10px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 500px;
            margin: 0 auto;
        }

        .question-divider {
            border-bottom: 1px solid #ccc;
            margin-bottom: 20px;
            padding-bottom: 10px;
            text-align: center;
        }

        form input[type="radio"] {
            margin-right: 5px;
            vertical-align: middle;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: white;
            border: none;
            padding: 10px 30px;
            font-size: 18px;
            cursor: pointer;
            border-radius: 3px;
            transition: background-color 0.3s ease;
            margin-top: 20px;
            display: block;
            margin-left: auto;
            margin-right: auto;
        }

        input[type="submit"]:hover {
            background-color: #FFD700;
        }

        .vocabulary {
            margin-top: 30px;
        }

        .vocabulary p {
            margin-bottom: 15px;
        }

        body {
            display: grid;
            gap: 0;
            margin: 0;
            grid-template-columns: repeat(5, 1fr);
            grid-template-rows: 40px 1fr 50px;
            grid-template-areas:
                "header header header header header"
                "content content content content content"
                "footer footer footer footer footer";
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
        .fixed-header {
            grid-area: header;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 40px;
            background-color: #333;
            color: #fff;
        }

        .header-content {
            display: grid;
            grid-template-columns: 1fr 60% 1fr;
        }

        .header-content .trang-chu {
            font-size: 30px;
            text-decoration: none;
            display: flex;
            align-items: center;
            justify-content: center;
            color: aliceblue;
        }

        .header-content .item {
            font-size: 24px;
            text-decoration: none;
            display: flex;
            align-items: center;
            justify-content: center;
            color: bisque;
        }

        .right a {
            text-decoration: none;
            margin-right: 20px;
            color: bisque;
            font-size: 20px;
        }

        footer {
            grid-area: footer;
            background-color: #333;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        main {
            grid-area: content;
            margin-bottom: 100px;
        }
        /* Thêm class mới cho các đáp án */
        form label.answer {
            font-size: 22px;
        }

        /* Thêm class mới cho nút submit */
        input[type="submit"] {
            background-color: goldenrod;
            color: #333;
            cursor: pointer;
            transition: background-color 0.3s;
        }



        /* Tăng kích thước của input khi hover */




    </style>

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
        <main>
            <h1>${a.tenKH}</h1>
            <p style="text-align:center;margin-bottom:15px;">${d.content}</p>
            <%
                ArrayList<blogQuestion> b = (ArrayList) request.getAttribute("b");
                if (b.size() > 0) {%>
            <form action="check" method="post">
                <% int k = 1;%>
                <p style="margin-bottom:25px;text-align: center;">DANH SÁCH QUESTIONS</p>
                <c:forEach items="${b}" var="o">
                    <p>Câu <%=k%>: ${o.question}</p>
                    <input type='hidden' name='ques<%=k%>' value='${o.question}'>
                    <label class="answer"><input type='radio' name='ans<%=k%>' value='a' required>${o.a}</label></br>
                    <label class="answer"><input type='radio' name='ans<%=k%>' value='b' required>${o.b}</label></br>
                    <label class="answer"><input type='radio' name='ans<%=k%>' value='c' required>${o.c}</label></br>
                    <label class="answer"><input type='radio' name='ans<%=k%>' value='d' required>${o.d}</label></br><hr style="margin-top:25px;">
                        <% k++;%>
                    </c:forEach>   
                <input type="hidden" name="cnt" value="<%=k%>">
                <input type="hidden" name="id" value="${a.id}">
                <input type='submit'>
            </form>

            <c:forEach items="${c}" var="o">
                <p>${o.word}(${o.getPhatAmTV()}): ${o.getNghiaTV()}</p>
            </c:forEach>    
            <%} else {
                VocabularyDAO vocabularyDAO = new VocabularyDAO();
                int id = (Integer) request.getAttribute("id");
                ArrayList<Vocabulary> c = vocabularyDAO.timTatCaTuMoiBangCourse(id);
                for (Vocabulary x : c) {%>
            <p><%=x.getWord()%>(<%=x.getPhatAmTV()%>): <%=x.getNghiaTV()%></p>
            <%}
                }
            %>
        </main>
    </body>
</html>

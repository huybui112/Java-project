<%-- 
    Document   : editblog
    Created on : Nov 17, 2023, 10:59:47 AM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./static/editblog.css">
        <style>
            .input-container {
                margin-bottom: 10px;
            }
            .sub {
                font-size: 20px;
                background-color: goldenrod;
                color: #333;
                cursor: pointer;
                transition: background-color 0.3s ease, color 0.3s ease, box-shadow 0.3s ease;
            }

            .sub:hover {
                background-color: #FFD700;
                color: #fff;
                box-shadow: 0 0 10px rgba(218, 165, 32, 0.5);
            }
        </style>
    </head>
    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="home.jsp">Trang Chủ</a>

                <a class="item " href="list">Danh Sách Blog</a>

                <div class="item right">
                    <a id="info " class="team1" href="thongtinnguoidung.jsp">Thông Tin </a>
                    <a id="logout " class="team2" href="LogoutProcessServlet">Đăng Xuất</a>

                </div>


            </div>
        </header>
        <main class="main-content">
            <h1 style="font-size: 30px">Edit Blog</h1>
            <form action="edit1" method="post">
                <label for="name">TITLE</label>
                <input class="title" type="text" id="name" maxlength="30" name="title" value="${a.tenKH}" required>
                <label for="description">ESSAY</label>
                <textarea  type="text" id="description" rows="4" cols="50" name="essay"  required>${d.content}</textarea>
            </form>
                <h1>Edit Questions</h1>
                <% int k = 1;%>
            <c:forEach items="${b}" var="o">
                
                <form action="edit2" method="post">
                Question <%=k%>:<input class="inques" input type='text' name='question' value='${o.question}'></br>
                <div style="font-size: 20px;">
                        A:<input class="thea inthe"  type="text" name="ans1" value="${o.a}">
                        B:<input class="theb inthe" class="inques"type="text" name="ans2" value="${o.b}"><br>
                        C:<input class="thec inthe" class="inques"type="text"name="ans3" value="${o.c}">
                        D:<input class="thed inthe"  class="inques"  type="text"name="ans4" value="${o.d}"><br>
                        Answer:<input style="margin-right: 80px;" class="thecheck inthe" "type="text"name="answer" value="${o.answer}">
                    </div>
                
                <% k++; %>
                <input type="hidden" name="qid" value="${o.id}">
                <input type="hidden" name="id" value="${a.id}">
                <input class="edit-button" type='submit' value="Edit">
                <a class="delete-link" href="deleteblogquestion?qid=${o.id}&id=${a.id}">Delete</a>
                </form>
            </c:forEach>
        
        
            <form action="addblogquestion" method="post">
                Questions:<input class="inques" input type='text' name='question' ></br>
                <div style="font-size: 20px;">
                    A:<input class="thea inthe"  type="text" name="A0">
                    B:<input class="theb inthe" class="inques"type="text" name="B0"><br>
                    C:<input class="thec inthe" class="inques"type="text"name="C0">
                    D:<input class="thed inthe"  class="inques"  type="text"name="D0"><br>
                    Answer:<input style="margin-right: 80px;" class="thecheck inthe" "type="text"name="answer0">
                </div>
                
                <input type="hidden" name="id" value="${a.id}">
                <input class="sub" type='submit' value="Add">
            </form>
            <h1>Edit Vocabularys</h1>
                <i style="color: red;  margin-top: 20px; margin-bottom: 20px; font-size: 25px">${error_editVocab}</i>
                <c:forEach items="${c}" var="o">
                    <form action="edit3" method="post">
                    <input class="ques" type="text" name="vocab" value="${o.word}">
                    <input type="hidden" name="id" value="${a.id}">
                    <input type="hidden" name="vid" value="${o.id}">
                    <input class="edit-button" type='submit' value="Edit">
                    <a class="delete-link" href="deletevocabblog?vid=${o.id}&id=${a.id}">Delete</a></br>
                    </form>
                </c:forEach> 
        
            <%
                String error_vocab = (String) request.getAttribute("error_vocab");
                if(error_vocab == null)
                {%>
                    <form action="addvocabblog?action=1" method="post">
                        <input type="hidden" name="id" value="${a.id}">
                        <input class="ques" type="text" name="vocab">
                        <input class="sub" style="font-size: 18px;margin-left: 10px;margin-top:50px;" type='submit' value="Add">
                    </form>
                <%}
                else
                {%>
                    <i style="color: red; margin-top: 20px; margin-bottom: 20px; font-size: 25px">${error_vocab}</i>
                    <form action="addvocabblog?action=2" method="post">
                    <input type="hidden" name="id" value="${a.id}">
                        Word: <input class="form-add" type="text" name="word" required="true">
                        Pronunciation: <input class="form-add" type="text" name="pronunciation" required="true">
                        Part Of Speech: <input class="form-add" type="text" name="partOfSpeech" required="true">
                        Mean: <input class="form-add" type="text" name="mean" required="true">
                        Synonym: <input class="form-add" type="text" name="syn">
                        Antonym: <input class="form-add" type="text" name="anton">
                        <input class="sub" style="font-size: 18px;margin-left: 10px;margin-top:50px;" type='submit' value="Add">
                    </form>                
                <%}
            %>
            
        </main>
        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>
</html>
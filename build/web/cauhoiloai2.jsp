<%-- 
    Document   : cauhoiloai2
    Created on : Nov 15, 2023, 6:24:20 PM
    Author     : nhanv
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.QuestionVoca" %>
<%@page import="model.Vocabulary" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="model.Vocabulary"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" style="height: 100%;">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="static/cauhoiloai2.css">
        <title>Trang chủ</title>
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
                    <a id="info " href="thongtinnguoidung.jsp">Thông Tin </a>
                    <a id="logout " href="LogoutProcessServlet">Đăng Xuất</a>
                </div>
                <%}
                %>

            </div>
        </header>
        <main class="main-content">
            <form id="quizForm" action="kiemtra" method="get">
                <%
                    QuestionVoca question = (QuestionVoca) request.getAttribute("question");
                    String ans = null;
                    int type = question.getType();
                    int idv = question.getVocabulary().getId();
                    String word = question.getVocabulary().getWord();
                    String mean = question.getVocabulary().getNghiaTV();
                    int stt = Integer.parseInt("" + request.getAttribute("stt"));
                    ArrayList<QuestionVoca> list = (ArrayList<QuestionVoca>) session.getAttribute("listQuestion");
                    int point = Integer.parseInt("" + request.getAttribute("point"));

                    
                    String display = mean;
                    StringBuilder resultBuilder = new StringBuilder();

                    String[] parts = mean.split("\\*");

                    for (String part : parts) {
                        String[] lines = part.split("\\-");

                        if (lines.length > 1) {
                            resultBuilder.append(lines[0].trim()).append(":");
                            resultBuilder.append(lines[1].trim()).append(". --");
                        }
                    }
                    int lres = resultBuilder.length();
                    if(lres>=2){
                        resultBuilder.delete(lres-2, lres);
                        display = resultBuilder.toString();
                    }
                    
                %>
                <h2 style="margin-left: 30px"><%=stt%>/ <%=list.size()%></h2>

                <legend style="font-size: 30px"> Từ nào có nghĩa sau: <%=display%> ?</legend>
                <%
                    ans = question.getVocabulary().getWord();

                %>

                <input name="selectedValue" >
                <input type="hidden" name="stt" value="<%=stt%>">
                <input type="hidden" name="ans" value="<%=ans%>">
                <input type="hidden" name="idv" value="<%=idv%>">
                <input type="hidden" name="point" value="<%=point%>">


                <button type="submit" class="submit-form" >Submit</button>
            </form>
        </main>



        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>

</html>
<%-- 
    Document   : thongtinkhoahoc
    Created on : Nov 6, 2023, 11:51:35 PM
    Author     : nhanv
--%>
<%@page import="model.QuestionVoca"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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
        <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        form {
            margin-top: 20px;
        }

        fieldset {
            border: none;
            padding: 0;
            margin: 0;
        }

        legend {
            margin-bottom: 10px;
            color: #555;
        }

        .grid-container {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            grid-gap: 10px;
        }

        label {
            display: flex;
            align-items: center;
            justify-content: center;
            /* Căn giữa theo chiều ngang */
            height: 50px;
            /* Điều chỉnh chiều cao ô vuông */
            color: #555;
            border: 1px solid #ddd;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        label.selected {
            background-color: #4caf50;
            color: #fff;
        }

        label:hover {
            background-color: #f9f9f9;
        }

        input[type="radio"] {
            display: none;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
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
            <h1>Câu hỏi ôn tập</h1>
            <form id="quizForm" action="ontap">
                <fieldset>
                    <legend>${idv+1}. What is the meaning of the word "${ques.word}"?</legend>
                    <% QuestionVoca a = (QuestionVoca) request.getAttribute("ques"); 
                       ArrayList<String> t = a.getDapan();
                       int q = 0;
                    %>
                    <div class="grid-container">
                        <% if(a.getDapan().size() == 4 ){%>
                        <c:forEach items="${ques.dapan}" var="voca" varStatus="loop">
                        <label>
                            <% if (t.get(q).endsWith("..")) { 
                                String e = "";
                                for(int i=0;i< t.get(q).length()-2;i++){
                                    e+=t.get(q).charAt(i);
                                }
                            %>
                                <input type="radio" name="ans${idv+1}" value="${voca}"> <%=e%>
                            <% } else{%>
                                <input type="radio" name="ans${idv+1}" value="${voca}"> ${voca}
                            <% } %>
                        </label>
                        <% q++;%>
                        </c:forEach>
                        <% } else {%>
                            <input type="text" name="ans${idv+1}" value="${voca}">
                        <% } %>
                    </div>
                </fieldset>
                <input type ="hidden" name = "word" value = "${ques.word}">
                <input type ="hidden" name = "idv" value = "${idv+1}">
                <input type ="hidden" name = "idc" value = "${idc}">
                <input type ="hidden" name = "tu1" value = "${tu1}">
                <input type ="hidden" name = "ma" value = "${ma}">
                <input type="submit">


                <!-- chỉ để đổi màu đáp án khi chọn  -->
                <script>
                    function submitForm() {
                        const selectedLabel = document.querySelector('.grid-container label.selected');
                        if (selectedLabel) {
                            const selectedValue = selectedLabel.querySelector('input').value;


                            const form = document.createElement('form');
                            form.method = 'POST';
                            form.action = 'YourServletURL'; // Thay thế  bằng URL thực tế của servlet

                            const input = document.createElement('input');
                            input.type = 'hidden';
                            input.name = 'selectedValue'; // Tên trường sẽ được gửi đến servlet
                            input.value = selectedValue;

                            form.appendChild(input);

                            document.body.appendChild(form);

                            form.submit();
                        } else {
                            alert('Please select an answer before submitting.');
                        }
                    }

                    document.addEventListener('DOMContentLoaded', function () {
                        const labels = document.querySelectorAll('.grid-container label');

                        labels.forEach(function (label) {
                            label.addEventListener('click', function () {
                                // Remove 'selected' class from all labels
                                labels.forEach(function (l) {
                                    l.classList.remove('selected');
                                });

                                // Add 'selected' class to the clicked label
                                label.classList.add('selected');
                            });
                        });
                    });

                </script>
            </form>
                    <!-- Thêm các mục từ vựng khác tại đây -->

        </main>



        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>

</html>


<%-- 
    Document   : cauhoiloai1
    Created on : Nov 10, 2023, 10:56:24 PM
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
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="static/cauhoiloai1.css">
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
            <form id="quizForm">
                <fieldset class="form-center">

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
                    %>
                    <h2><%=stt%>/ <%=list.size()%></h2>
                    <%

                        if (type == 1) {


                    %>

                    <legend style="font-size: 30px"> Nghĩa của từ " <%=word%> " là?</legend>
                    <%
                        ans = question.getVocabulary().getNghiaTV();
                    } else {
                        String displayMean = mean;
                   
                        StringBuilder resultBuilder = new StringBuilder();

                        String[] parts = mean.split("\\*");

                        for (String part : parts) {
                            String[] lines = part.split("\\-");

                            if (lines.length > 1) {
                                resultBuilder.append(lines[0].trim()).append(":");
                                resultBuilder.append(lines[1].trim()).append(". ");
                            }
                        }
                        int lres = resultBuilder.length();
                        if (lres >= 2) {
                            resultBuilder.delete(lres - 2, lres);

                        displayMean = resultBuilder.toString();
                        }
                    %>

                    <legend style="font-size: 30px"> Từ nào có nghĩa sau: <%=displayMean%> ?</legend>
                    <%
                            ans = question.getVocabulary().getWord();
                        }
                    %>
                    <div class="grid-container">
                        <%
                            for (String s : question.getOption()) {
                                String display = s;
                                if (type == 1) {
                                    StringBuilder resultBuilder = new StringBuilder();

                                    String[] parts = s.split("\\*");

                                    for (String part : parts) {
                                        String[] lines = part.split("\\-");

                                        if (lines.length > 1) {
                                            resultBuilder.append(lines[0].trim()).append(": ");
                                            resultBuilder.append(lines[1].trim()).append(".--");
                                        }
                                    }
                                    int lres = resultBuilder.length();
                                    if (lres >= 2) {
                                        resultBuilder.delete(lres - 2, lres);
                                        // Lưu chuỗi kết quả vào biến result
                                    display = resultBuilder.toString();
                                    }
                                    
                                }


                        %>
                        <label>
                            <input type="radio" name="question1" value="<%=s%>"> <%=display%>
                        </label>
                        <%-- Đặt các hidden field trong vòng lặp để chúng được tạo cho mỗi lựa chọn --%>
                        <input type="hidden" name="stt" value="<%=stt%>">
                        <input type="hidden" name="ans" value="<%=ans%>">
                        <input type="hidden" name="idv" value="<%=idv%>">
                        <input type="hidden" name="point" value="<%=point%>">
                        <%
                            }
                        %>
                    </div>
                </fieldset>
                    <button type="button" onclick="submitForm()" class="submit-form" style="font-size: 30px; align-items: center; justify-content: center">Submit</button>

                <!-- chỉ để đổi màu đáp án khi chọn  -->
                <script>
                    function submitForm() {
                        const selectedLabel = document.querySelector('.grid-container label.selected');
                        if (selectedLabel) {
                            const selectedValue = selectedLabel.querySelector('input').value;

                            const form = document.createElement('form');
                            form.method = 'GET';
                            form.action = 'kiemtra'; // Thay thế bằng URL thực tế của servlet

                            const input = document.createElement('input');
                            input.type = 'hidden';
                            input.name = 'selectedValue'; // Tên trường sẽ được gửi đến servlet
                            input.value = selectedValue;

                            const stt = document.querySelector('.grid-container input[name="stt"]');
                            const inputstt = document.createElement('input');
                            inputstt.type = 'hidden';
                            inputstt.name = 'stt'; // Tên trường sẽ được gửi đến servlet
                            inputstt.value = stt.value;

                            const ans = document.querySelector('.grid-container input[name="ans"]');
                            const inputans = document.createElement('input');
                            inputans.type = 'hidden';
                            inputans.name = 'ans'; // Tên trường sẽ được gửi đến servlet
                            inputans.value = ans.value;


                            const idv = document.querySelector('.grid-container input[name="idv"]');
                            const inputidv = document.createElement('input');
                            inputidv.type = 'hidden';
                            inputidv.name = 'idv'; // Tên trường sẽ được gửi đến servlet
                            inputidv.value = idv.value;


                            const point = document.querySelector('.grid-container input[name="point"]');
                            const inputpoint = document.createElement('input');
                            inputpoint.type = 'hidden';
                            inputpoint.name = 'point'; // Tên trường sẽ được gửi đến servlet
                            inputpoint.value = point.value;


                            form.appendChild(input);
                            form.appendChild(inputstt);
                            form.appendChild(inputans);
                            form.appendChild(inputidv);
                            form.appendChild(inputpoint);

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
        </main>



        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>

</html>
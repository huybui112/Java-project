<%-- 
    Document   : flashcard.jsp
    Created on : Nov 11, 2023, 4:11:36 PM
    Author     : nhanv
--%>



<%@page import="model.User"%>
<%@page import="model.Vocabulary"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" style="height: 100%;">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="static/flashcard.css">
        <title>Trang chủ</title>

        <style>
            .scroll-mean {
                max-height: 250px;
                overflow-y: auto;
                border: 1px solid #ccc;
                width: 380px;
                text-align: center;
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
                    <a id="info " href="thongtinnguoidung.jsp">Thông Tin </a>
                    <a id="logout " href="LogoutProcessServlet">Đăng Xuất</a>
                </div>
                <%}
                %>

            </div>
        </header>
        <main class="main-content">




            <div class="before" onmouseover="addHoverClass(this)" onmouseout="removeHoverClass(this)">

                <a href="flashcard?idv=${idv-1}&idc=${idc}">Before</a>
            </div>


            <div class="flashcard" onclick="flipCard()">
                <div class="card" >
                    <div class="front">
                        <h2>${voca.word}</h2>
                        <button>Flip</button>
                    </div>
                    <div class="back">
                        <h2>${voca.word}</h2>
                        <p>${voca.phatAmTV}</p>
                        <div class="scroll-mean">
                            <!--<p>${voca.nghiaTV}</p>-->

                            <%
                                Vocabulary vocabulary = (Vocabulary) request.getAttribute("voca");
                                String[] a = vocabulary.getNghiaTV().split("\\s+");
                                String mean = a[0];
                                for (int i = 1; i < a.length; i++) {
                                    if (a[i].equals("*") || a[i].equals("-")) {%>
                            <p><%=mean%></p>
                            <%
                                        mean = a[i];
                                    } else {
                                        mean += (" " + a[i]);
                                    }
                                }
                            %>
                            <p><%=mean%></p>
                        </div>

                        <p>${voca.ghiChu}</p>
                        <button>Flip Back</button>
                    </div>
                </div>
            </div>
            <div class="next" onmouseover="addHoverClass(this)" onmouseout="removeHoverClass(this)">
                <a href="flashcard?idv=${idv+1}&idc=${idc}">Next</a>
            </div>
            <div class="process">
                <p style="margin-top: 200px;">${idv+1} / ${size} </p> 
            </div>
            <script>
                function flipCard() {
                    const card = document.querySelector('.flashcard');
                    card.classList.toggle('flipped');

                }
                function addHoverClass(element) {
                    element.classList.add("hover");
                }

                function removeHoverClass(element) {
                    element.classList.remove("hover");
                }
            </script>


        </main>



        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>

</html>
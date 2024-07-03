<%-- 
    Document   : kiemtradapan
    Created on : Nov 15, 2023, 3:38:05 PM
    Author     : nhanv
--%>

<%@page import="dao.VocabularyDAO"%>
<%@page import="model.User"%>
<%@page import="model.Vocabulary"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" style="height: 100%">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="static/kiemtradapan.css">
        <title>Đáp Án</title>
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
        <%
            VocabularyDAO vd = new VocabularyDAO();
            int idv = Integer.parseInt(request.getParameter("idv"));
            Vocabulary v = vd.timTuMoiBangId(idv);

%>
        <main class="main-content">
            <h2 class="check-link">${mess}</h2>
            
            <div class="flashcard" onclick="flipCard()">
                <div class="card" >
                    <div class="front">
                        <h2><%=v.getWord()%></h2>
                        <button>Flip</button>
                    </div>
                    <div class="back">
                        <h2><%=v.getWord()%></h2>
                        <p><%=v.getPhatAmTV()%></p>
                        <div class="scroll-mean">

                            <%
                                
                                String[] a = v.getNghiaTV().split("\\s+");
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

                        <p><%=v.getGhiChu()%></p>
                        <button>Flip Back</button>
                    </div>
                </div>
            </div>

            <a class="continue-link" href="ontap?stt=${stt}&point=${point}"  >Tiếp tục</a>

            <script>
                function flipCard() {
                    const card = document.querySelector('.flashcard');
                    card.classList.toggle('flipped');
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

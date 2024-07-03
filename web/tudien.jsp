<%-- 
    Document   : tudien.jsp
    Created on : Nov 14, 2023, 8:56:53 PM
    Author     : ASUS
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Dictionary, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dictionary</title>
        <style>
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

            h1 {
                color: #333;
            }

            form {
                display: inline-block; /* Hiển thị ô search và nút submit trên cùng một dòng */
                margin-top: 20px;
            }

            label {
                font-size: 20px;
                margin-right: 10px;
            }

            input[type="text"] {
                padding: 15px;
                font-size: 17px;
            }
            input[type="submit"] {

                padding: 15px;
                margin-top: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }


            a {
                text-decoration: none;
                color: #007BFF;
                margin-left: 10px; /* Thêm khoảng cách giữa ô search và nút 'Thêm từ mới' */
            }

            a:hover {
                color: #0056b3;
            }

            hr {
                border: 1px solid #ddd;
            }
            p{
                font-size: 20px;
                margin: 12px;
                padding: 1px;
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

            footer {
                grid-area: footer;
                background-color: #333;
                color: #fff;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 60px; /* Chiều cao footer */
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
            main {
                grid-area: content;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                padding: 20px;
            }
            hr {
                border: 1px solid #ddd;
                margin: 10px 0; /* Giảm khoảng cách trên và dưới đường kẻ */
            }
            h1 {
                color: #333;
                font-size: 24px;
            }

            a {
                text-decoration: none;
                color: #007BFF;
                margin-left: 10px;
            }

            a:hover {
                color: #0056b3;
            }
            hr {
                border: none; 
                height: 1px;  
                background-color: #ddd;  
                margin: 10px 0;  
                width: 353px;
            }
            div {
                margin-bottom: 20px; /* Khoảng cách giữa hai div */
            }
            .div1 {
/*                display: flex;*/
                flex-direction: column;
                align-items: center;
                justify-content: center;
                text-align: center;
                margin: 10px auto;
                width: 50%;
                border: 1px solid #ddd;
                border-radius: 5px;
                padding: 25px;
                background-color: #f9f9f9;
                width: 300px;
            }

            input[type="submit"] {
                background-color: goldenrod;
                color: #333;
                cursor: pointer;
                margin-left: 15px;
                transition: background-color 0.3s ease, color 0.3s ease, box-shadow 0.3s ease;
            }

            input[type="submit"]:hover {
                background-color: #FFD700; /* Một tông màu goldenrod khác để làm nổi bật hơn */
                color: #fff; /* Màu chữ trắng khi hover */
                box-shadow: 0 0 10px rgba(218, 165, 32, 0.5); /* Hiệu ứng shadow khi hover */
            }
            .s {
                text-decoration: none;
                color: windowframe;
                margin-left: 10px;
                padding: 8px 15px;
                border: 1px solid #333;
                border-radius: 5px;
                transition: background-color 0.3s ease, color 0.3s ease, box-shadow 0.3s ease;
            }

            .s:hover {
                background-color: #333;
                color: #fff;
                box-shadow: 0 0 10px rgba(51, 51, 51, 0.5);
            }
        </style>
    </head>
    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="home"; style="font-size: 30px">Trang Chủ</a>
            </div>
        </header>

        <main>
            <%
                boolean check = false;
                User user = (User) session.getAttribute("user");
                if (user.getUsername().equals("user1")) {
                    check = true;
            %>

            <%
                }
                ArrayList<Dictionary> dict = (ArrayList<Dictionary>) request.getAttribute("dict");
                Integer pageNum = (Integer) request.getAttribute("page");
                Integer maxPage = (Integer) request.getAttribute("maxPage");
                String words = request.getParameter("words");
                if (words == null)
                    words = "";
            %>
            <h1 style="font-size: 30px;padding-bottom: 10px;">THIS IS DICTIONARY</h1>
            <a href="editDictionary?action=add" style="font-size: 20px;">THÊM TỪ MỚI</a>
            <div>
                <form method="get" action="dict">
                    <label for="wordsId">WORD</label>
                    <input type="text" name="words" id="wordsId">
                    <input type="hidden" name="page" value="0">
                    <input type="submit" value="SEARCH">
                </form>
            </div>
            <%
                if (dict.size() != 0) {
                    for (Dictionary x : dict) {%>
            <div class="div1">
                <p>Word: <%=x.getWord()%></p>
                <p>Pronunciation: <%=x.getPronunciation()%></p>
                <p>Part of Speech: <%=x.getPartOfSpeech()%></p>
                <p>Meaning: </p>
                <%
                    String[] a = x.getMean().split("\\s+");
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
                <%
                    if (check) {%>
                <a class="s" href="editDictionary?action=edit&wordId=<%=x.getId()%>">Sửa</a>
                <a class="s" href="editDictionary?action=delete&wordId=<%=x.getId()%>">Xóa</a>
                <%}
                %>
            </div>
            
            <%
                    }
                }
            %>
            <hr>
            <div style="padding-top: 20px;font-size:20px">
                <a href="dict?words=<%=words%>&page=<%=pageNum > 0 ? pageNum - 1 : 0%>">Previous</a>
                <a href="dict?words=<%=words%>&page=0">0</a>
                <%
                    if (pageNum > 3) {%>
                ...
                <a href="dict?words=<%=words%>&page=<%=pageNum - 2%>"><%=pageNum - 2%></a>
                <a href="dict?words=<%=words%>&page=<%=pageNum - 1%>"><%=pageNum - 1%></a>
                <%} else {
                    for (int i = 1; i < pageNum && i < maxPage; i++) {%>
                <a href="dict?words=<%=words%>&page=<%=i%>"><%=i%></a>
                <%}
                    }
                    if (pageNum > 0 && pageNum < maxPage) {%>
                <a href="dict?words=<%=words%>&page=<%=pageNum%>"><%=pageNum%></a>
                <%}
                    if (pageNum < maxPage - 3) {%>
                <a href="dict?words=<%=words%>&page=<%=pageNum + 1%>"><%=pageNum + 1%></a>
                <a href="dict?words=<%=words%>&page=<%=pageNum + 2%>"><%=pageNum + 2%></a>
                ...
                <%} else {
                    for (int i = pageNum + 1; i < maxPage; i++) {%>
                <a href="dict?words=<%=words%>&page=<%=i%>"><%=i%></a> 
                <%}
                    }
                    if (maxPage != 0) {%>
                <a href="dict?words=<%=words%>&page=<%=maxPage%>"><%=maxPage%></a>
                <%}
                %>
                <a href="dict?words=<%=words%>&page=<%=pageNum < maxPage ? pageNum + 1 : 0%>">Next</a>
            </div>
        </main>
        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>

    </body>
</html>

<%-- 
    Document   : danhsachkh
    Created on : Nov 3, 2023, 11:38:55 PM
    Author     : nhanv
--%>
<%@page import="model.Vocabulary"%>
<%@page import="dao.ProcessDAO"%>
<%@page import="dao.VocabularyDAO"%>
<%@page import="model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="model.Process"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="static/danhsachkh.css">
        <title>Trang chủ</title>
    </head>

    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="home.jsp">Trang Chủ</a>
                <%
                    User user = (User) session.getAttribute("user");
                    boolean check = false;
                    if (user != null) {%>
                <%
                    if (user.getUsername().equals("user1")) {
                        check = true;
                %>
                <a class = "item" href="themblog.jsp">Thêm Blog</a>
                <%}
                %>
                <div class="item right">
                    <a class="item" class="team3" href="dict?page=0">Dictionary</a>
                </div>
                <div class="item right">
                    <a id="info" class="team1" href="thongtinnguoidung.jsp">Thông Tin </a>
                 </div>
                <div class="item right">
                    <a id="logout " class="team2" href="LogoutProcessServlet">Đăng Xuất</a> 
                    </div>
                
                <%}
                %>
            </div>
        </header>
        <%
            int pageNum = (Integer) request.getAttribute("page");
            int maxPage = (Integer) request.getAttribute("maxPage");
            String filter = (String) request.getAttribute("filter");
            ArrayList<Course> a = (ArrayList<Course>) request.getAttribute("listCourse");

        %>
        <main class="main-content">
            <div class="select-list">
                <div class="title">
                    <h1>Danh Sách Blog</h1>
                </div>
                <form style="margin-bottom: 50px;"action="list?page=0" method="get">

                    <select name="filter">
                        <option id="option-all" value="all" >Tất cả các blog</option>
                        <%                            if (filter.equals("newest")) {%>
                        <option id="option-own" value="newest" selected="true">Các blog tạo gần đây nhất</option>
                        <%} else {%>
                        <option id="option-own" value="newest">Các blog tạo gần đây nhất</option>
                        <%}
                            if (filter.equals("oldest")) {%>
                        <option id="option-join" value="oldest" selected="true">Các blog tạo lâu nhất</option>
                        <%} else {%>
                        <option id="option-join" value="oldest">Các blog tạo lâu nhất</option>
                        <%}
                        %>                        

                    </select>
                    <input type="submit" value="SEARCH"/>
                </form>
            </div>
            <%
                int k = 0;
            %>
            <div class="list-courses">
                <c:forEach items="${listCourse}" var="list">
                    <%
                        int res = 0;
                        VocabularyDAO vocabularyDAO = new VocabularyDAO();
                        ProcessDAO processDAO = new ProcessDAO();
                        ArrayList<Vocabulary> list = vocabularyDAO.timTatCaTuMoiBangCourse(a.get(k).getId());
                        for (Vocabulary x : list) {
                            Process p = processDAO.findProcess(user.getId(), x.getId());
                            if (p != null) {
                                if (p.getProcess() == 1) {
                                    res++;
                                }
                            }

                        }
                    %>
                    <div class="item-card">
                        <div class="card name">
                            <a href="thongtinblog?id=${list.id}"><p style="font-size: 30px;">${list.tenKH}</p></a>
                        </div>
                        <div class="card process">

                            <c:if test="${list.getTongSoTu() > 0}">
                                <p id="percent">COMPLETE <%=res%> /${list.getTongSoTu()}</p>
                            </c:if>
                        </div>


                        <div class="card-btn">

                            <c:if test="${list.getTongSoTu() > 0}">
                                <a href="flashcard?idv=0&idc=${list.id}">HỌC</a>
                                <a href="batdau?idc=${list.id}">ÔN TẬP</a>
                                <a href="resetProcess?idc=${list.id}">RESET</a> 
                            </c:if>




                            <%
                                if (check) {%>
                            <a href="editblog?id=${list.id}">EDIT</a>
                            <a href="deleteblog?id=${list.id}">DELETE</a>
                            <%}
                            %>
                        </div>
                    </div>
                    <% k++;%>
                </c:forEach>
            </div>
            <hr>
            <div style="text-align:center;padding-top: -10px;font-size:20px">
                <a href="list?filter=<%=filter%>&page=<%=pageNum > 0 ? pageNum - 1 : 0%>">Previous</a>
                <a href="list?filter=<%=filter%>&page=0">0</a>
                <%
                    if (pageNum > 3) {%>
                ...
                <a href="list?filter=<%=filter%>&page=<%=pageNum - 2%>"><%=pageNum - 2%></a>
                <a href="list?filter=<%=filter%>&page=<%=pageNum - 1%>"><%=pageNum - 1%></a>
                <%} else {
                    for (int i = 1; i < pageNum && i < maxPage; i++) {%>
                <a href="list?filter=<%=filter%>&page=<%=i%>"><%=i%></a>
                <%}
                    }
                    if (pageNum > 0 && pageNum < maxPage) {%>
                <a href="list?filter=<%=filter%>&page=<%=pageNum%>"><%=pageNum%></a>
                <%}
                    if (pageNum < maxPage - 3) {%>
                <a href="list?filter=<%=filter%>&page=<%=pageNum + 1%>"><%=pageNum + 1%></a>
                <a href="list?filter=<%=filter%>&page=<%=pageNum + 2%>"><%=pageNum + 2%></a>
                ...
                <%} else {
                    for (int i = pageNum + 1; i < maxPage; i++) {%>
                <a href="list?filter=<%=filter%>&page=<%=i%>"><%=i%></a> 
                <%}
                    }
                    if (maxPage != 0) {%>
                <a href="list?filter=<%=filter%>&page=<%=maxPage%>"><%=maxPage%></a>
                <%}
                %>
                <a href="list?filter=<%=filter%>&page=<%=pageNum < maxPage ? pageNum + 1 : 0%>">Next</a>
            </div>
            
        </main>
        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>

</html>

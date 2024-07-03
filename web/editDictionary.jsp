<%-- 
    Document   : editDictionary
    Created on : Nov 22, 2023, 10:27:47 PM
    Author     : ASUS
--%>

<%@page import="model.Dictionary"%>
<%@page import="dao.DictionaryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Dictionary</title>
        <link rel="stylesheet" href="./static/editdic.css">
    </head>
    <body>
        <header class="fixed-header">
            <div class="header-content">
                <a class="trang-chu" href="dict?page=0" >Quay lại từ điển</a>
            </div>
        </header>
        <div class="error-message">
            <h1><i style="color: red; font-style:normal;">${error}</i></h1>
        </div>
        <%
            String idString = request.getParameter("wordId");
            if (idString != null) {
                int id = Integer.parseInt(idString);
                DictionaryDAO dictionaryDAO = new DictionaryDAO();
                Dictionary dictionary = dictionaryDAO.timTuBangId(id);
        %>
        
        <div>
            
            <form action="editDictionary?action=<%=request.getParameter("action")%>&wordId=<%=id%>" method="post">
        
                <div>
                    <label for="wordId">Word:</label>
                    <input type="text" name="word" id="wordId" value="<%=dictionary.getWord()%>" required="true">
                </div>
                <div>
                    <label for="pronunciationId">Pronunciation:</label>
                    <input type="text" name="pronunciation" id="pronunciationId" value="<%=dictionary.getPronunciation()%>" required="true">
                </div>
                <div>
                    <label for="partOfSpeechId">Part Of Speech:</label>
                    <input type="text" name="partOfSpeech" id="partOfSpeechId" value="<%=dictionary.getPartOfSpeech()%>" required="true">
                </div>
                <div>
                    <label for="meanId">Mean:</label>
                    <input type="text" name="mean" id="meanId" value="<%=dictionary.getMean()%>" required="true">
                </div>
                <div>
                    <label for="synId">Synonym:</label>
                    <input type="text" name="syn" id="synId" value="<%=dictionary.getSynonym()%>">
                </div>
                <div>
                    <label for="antonId">Antonym:</label>
                    <input type="text" name="anton" id="antonId" value="<%=dictionary.getAntonym()%>" >
                </div>
                <input type="submit" value="Sửa">
            </form>
        </div>
        <%
                } else {%>
        <div>
            
            <form action="editDictionary?action=<%=request.getParameter("action")%>" method="post">
                <div>
                    <label for="wordId">Word:</label>
                    <input type="text" name="word" id="wordId" required="true">
                </div>
                <div>
                    <label for="pronunciationId">Pronunciation:</label>
                    <input type="text" name="pronunciation" id="pronunciationId" required="true">
                </div>
                <div>
                    <label for="partOfSpeechId">Part Of Speech:</label>
                    <input type="text" name="partOfSpeech" id="partOfSpeech" required="true">
                </div>
                <div>
                    <label for="meanId">Mean:</label>
                    <input type="text" name="mean" id="meanId" required="true">
                </div>
                <div>
                    <label for="synId">Synonym:</label>
                    <input type="text" name="syn" id="synId">
                </div>
                <div>
                    <label for="antonId">Antonym:</label>
                    <input type="text" name="anton" id="antonId">
                </div>
                <input type="submit" style="font-size: 20px;" value="THÊM">
            </form>
            
        </div>
        <%}
        %>
    </body>
</html>
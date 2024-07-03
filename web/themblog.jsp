<%-- 
    Document   : themkhoahoc
    Created on : Nov 6, 2023, 7:07:24 PM
    Author     : nhanv
--%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thêm khóa học</title>
        <link rel="stylesheet" href="./static/themkhoahoc.css">
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
        <script>
            var inputCount = 1;
            var inputCountTu = 1;
            var inputCountTuMoi = 1;
            function themKhốiDiv(event) {
                event.preventDefault(); // Ngăn form submit
                var divCha = document.getElementById('div-cha');
                var divMoi = document.createElement('div');
                divMoi.className = 'input-container';

                // Thêm các thẻ input vào khối div mới
                var labelQuestions = document.createElement('label');
                labelQuestions.textContent = 'QUESTION:';
                var inputQuestions = document.createElement('input');
                inputQuestions.type = 'text';
                inputQuestions.name = 'questions' + inputCount;
                inputQuestions.classList.add('inques');
                inputQuestions.classList.add('marginques');

                divMoi.appendChild(labelQuestions);
                divMoi.appendChild(inputQuestions);
                divMoi.appendChild(document.createElement('br'));

                var labelA = document.createElement('label');
                labelA.textContent = 'A:';
                var inputA = document.createElement('input');
                inputA.type = 'text';
                inputA.name = 'A' + inputCount;
                inputA.classList.add('thea');
                inputA.classList.add('inthe');
                divMoi.appendChild(labelA);
                divMoi.appendChild(inputA);

                var labelB = document.createElement('label');
                labelB.textContent = 'B:';
                var inputB = document.createElement('input');
                inputB.type = 'text';
                inputB.name = 'B' + inputCount;
                inputB.classList.add('theb');
                inputB.classList.add('inthe');


                divMoi.appendChild(labelB);
                divMoi.appendChild(inputB);
                divMoi.appendChild(document.createElement('br'));

                var labelC = document.createElement('label');
                labelC.textContent = 'C:';
                var inputC = document.createElement('input');
                inputC.type = 'text';
                inputC.name = 'C' + inputCount;
                inputC.classList.add('thec');
                inputC.classList.add('inthe');


                divMoi.appendChild(labelC);
                divMoi.appendChild(inputC);

                var labelD = document.createElement('label');
                labelD.textContent = 'D:';
                var inputD = document.createElement('input');
                inputD.type = 'text';
                inputD.name = 'D' + inputCount;
                inputD.classList.add('thed');
                inputD.classList.add('inthe');


                divMoi.appendChild(labelD);
                divMoi.appendChild(inputD);
                divMoi.appendChild(document.createElement('br'));

                var labelAnswer = document.createElement('label');
                labelAnswer.textContent = 'Answer:';
                var inputAnswer = document.createElement('input');
                inputAnswer.type = 'text';
                inputAnswer.name = 'answer' + inputCount;
                inputAnswer.classList.add('inthe');
                inputAnswer.classList.add('thecheck');
//                inputAnswer.classList.add('marginans');
                divMoi.appendChild(labelAnswer);
                divMoi.appendChild(inputAnswer);

                // Tạo nút "Xóa" cho khối div mới
                var nutXoa = document.createElement('button');
                nutXoa.textContent = 'Xóa';
                nutXoa.onclick = function () {
                    divCha.removeChild(divMoi);
                };
                nutXoa.classList.add('button');
                divMoi.appendChild(nutXoa);


                // Tăng số thứ tự cho các thẻ input
                inputCount++;
                var inputCountInput = document.getElementById('inputCount');
                inputCountInput.value = inputCount;

                // Thêm khối div mới vào div cha
                divCha.appendChild(divMoi);
            }


            function themKhốiDivTu(event) {
                event.preventDefault(); // Ngăn form submit
                var divCha = document.getElementById('div-cha-tu');
                var divMoi = document.createElement('div');
                divMoi.className = 'input-container';

                // Thêm các thẻ input vào khối div mới
                var input = document.createElement('input');
                input.type = 'text';
                input.name = 'vocab' + inputCountTu;
                input.classList.add('voca1');
                divMoi.appendChild(input);
//                divMoi.appendChild(document.createElement('br'));

                // Tạo nút "Xóa" cho khối div mới
                var nutXoa = document.createElement('button');
                nutXoa.textContent = 'Xóa';
                nutXoa.classList.add('margintop');
                nutXoa.classList.add('button');
                nutXoa.onclick = function () {
                    divCha.removeChild(divMoi);
                };
                divMoi.appendChild(nutXoa);

                // Tăng số thứ tự cho các thẻ input
                inputCountTu++;
                var inputCountInput = document.getElementById('inputCountTu');
                inputCountInput.value = inputCountTu;
                // Thêm khối div mới vào div cha
                divCha.appendChild(divMoi);
            }

            function themKhốiDivTuVungMoi(event) {
                event.preventDefault(); // Ngăn form submit
                var divCha = document.getElementById('div-cha-tu-moi');
                var divMoi = document.createElement('div');
                divMoi.className = 'input-container';
                divMoi.appendChild(document.createElement('br'));
//                // Thêm các thẻ input vào khối div mới
//                var inputQuestions = document.createElement('input');
//                inputQuestions.type = 'text';
//                inputQuestions.name = 'questions' + inputCount;
//                divMoi.appendChild(document.createTextNode('Questions:'));
//                divMoi.appendChild(inputQuestions);
//                divMoi.appendChild(document.createElement('br'));

                var inputA = document.createElement('input');
                inputA.type = 'text';
                inputA.name = 'word' + inputCountTuMoi;
                inputA.classList.add('voca');
                inputA.classList.add('paddingvoca');
                divMoi.appendChild(document.createTextNode('Word:'));
                divMoi.appendChild(inputA);
                divMoi.appendChild(document.createElement('br'));

                var inputB = document.createElement('input');
                inputB.type = 'text';
                inputB.name = 'mean' + inputCountTuMoi;
                inputB.classList.add('voca');
                inputB.classList.add('paddingvoca');
                divMoi.appendChild(document.createTextNode('Mean:'));
                divMoi.appendChild(inputB);
                divMoi.appendChild(document.createElement('br'));

                var inputC = document.createElement('input');
                inputC.type = 'text';
                inputC.name = 'pronunciation' + inputCountTuMoi;
                inputC.classList.add('voca');
                inputC.classList.add('paddingvoca1');
                divMoi.appendChild(document.createTextNode('Pronunciation:'));
                divMoi.appendChild(inputC);
                divMoi.appendChild(document.createElement('br'));

                var inputD = document.createElement('input');
                inputD.type = 'text';
                inputD.name = 'partOfSpeech' + inputCountTuMoi;
                inputD.classList.add('voca');
                inputD.classList.add('xoaad');
                divMoi.appendChild(document.createTextNode('Part of speech:'));
                divMoi.appendChild(inputD);
//                divMoi.appendChild(document.createElement('br'));



//                var inputAnswer = document.createElement('input');
//                inputAnswer.type = 'text';
//                inputAnswer.name = 'answer' + inputCount;
//                divMoi.appendChild(document.createTextNode('Answer:'));
//                divMoi.appendChild(inputAnswer);

                // Tạo nút "Xóa" cho khối div mới
                var nutXoa = document.createElement('button');
                nutXoa.textContent = 'Xóa';
                nutXoa.classList.add('margintop');
                nutXoa.classList.add('button');
                nutXoa.onclick = function () {
                    divCha.removeChild(divMoi);
                };
                divMoi.appendChild(nutXoa);

                // Tăng số thứ tự cho các thẻ input
                inputCountTuMoi++;
                var inputCountInput = document.getElementById('inputCount');
                inputCountInput.value = inputCountTuMoi;
                // Thêm khối div mới vào div cha
                divCha.appendChild(divMoi);
            }
        </script>
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
            <h1 style="font-size: 30px;">THÊM BLOG </h1>
            <i style="font-style: normal;color: red;font-size: 24px;text-transform: uppercase;margin-bottom: 25px;">${message}</i>
            <form class="form-group" action="createBlog" method="post">
                <label for="name">TITLE</label>
                <input class="title" type="text" id="name" maxlength="30" name="title" value="${title}" required>

                <label for="description">ESSAY</label>
                <textarea  type="text" id="description" rows="4" cols="50" name="essay"  required>${essay}</textarea>

                <p style="font-size: 20px;margin-top: 25px;margin-bottom: -2px;">ADD QUESTION</p>
                <div id="div-cha">
                    <div class="input-container">
                        <p class="ques">QUESTION:<input class="inques" type="text" name="questions0"></p>
                        <div style="font-size: 20px;">
                            A:<input class="thea inthe"  type="text" name="A0">
                            B:<input class="theb inthe" class="inques"type="text" name="B0"><br>
                            C:<input class="thec inthe" class="inques"type="text"name="C0">
                            D:<input class="thed inthe"  class="inques"  type="text"name="D0"><br>
                            Answer:<input style="margin-right: 80px;" class="thecheck inthe" "type="text"name="answer0">
                        </div>
                    </div>
                </div>
                <input type="hidden" id="inputCount" name="inputCount" value="1">
                <button style="
                        text-decoration: none;
                        color: windowframe;
                        margin-left: 10px;
                        padding: 8px 15px;
                        border: 1px solid #333;
                        border-radius: 5px;
                        margin-top:25px;
                        transition: background-color 0.3s ease, color 0.3s ease, box-shadow 0.3s ease;"onclick="themKhốiDiv(event)">THÊM</button>


                <p style="font-size: 20px;">ADD VOCABULARY</p>
                <div id="div-cha-tu">
                    <div class="input-container">
                        <div>
                            <input style=""class="voca" type="text"name="vocab0">
                        </div>
                    </div>
                </div>
                <input type="hidden" id="inputCountTu" name="inputCountTu" value="1">
                <button class="them" onclick="themKhốiDivTu(event)">THÊM</button>

                <%
                    String message = (String) request.getAttribute("message");
                    if (message != null) {%>
                <p style="font-size: 16px;margin-top: 35px;">MORE VOCABULARY(NOT AVAILABLE IN DICTIONARY):</p>
                <div id="div-cha-tu-moi">
                    <div class="input-container">
                        <div>
                            Word:<input class="voca paddingvoca" type="text"name="word0"><br/>
                            Mean:<input class="voca paddingvoca" type="text"name="mean0"><br/>
                            Pronunciation:<input class="voca paddingvoca1" type="text"name="pronunciation0"><br/>
                            Part Of Speech:<input class="voca paddingvoca1" style="margin-right: 42px; margin-top:15px;" type="text"name="partOfSpeech0"><br/>
                        </div>
                    </div>
                </div>

                <input type="hidden" id="inputCountTu" name="inputCountTuMoi" value="1">
                <button class="them" onclick="themKhốiDivTuVungMoi(event)">THÊM</button>


                <%}
                %>
                <!--                <p>Thêm từ vựng(Không có sẵn trong từ điển):</p>
                                <div id="div-cha-tu-moi">
                                    <div class="input-container">
                                        <div>
                                        Word:<input type="text"name="word0"><br/>
                                        Mean:<input type="text"name="mean0"><br/>
                                        Pronunciation:<input type="text"name="pronunciation0"><br/>
                                        Part Of Speech:<input type="text"name="partOfSpeech0"><br/>
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" id="inputCountTu" name="inputCountTu" value="1">
                                <button onclick="themKhốiDivTuVungMoi(event)">Thêm</button>-->

                <input class="sub" style="font-size: 18px;margin-left: 10px;margin-top:50px;" type="submit" value="TẠO">

            </form>
        </main>
        <footer>
            <div class="footer-content">
                <!-- Nội dung footer (nếu cần) -->
            </div>
        </footer>
    </body>
</html>

///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package controller;
//
//import dao.CourseDAO;
//import dao.DictionaryDAO;
//import dao.ProcessDAO;
//import dao.VocabularyDAO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import model.Course;
//import model.Process;
//import model.Dictionary;
//import model.QuestionVoca;
//import model.User;
//import model.Vocabulary;
//
///**
// *
// * @author ADMIN
// */
//@WebServlet(name = "OnTap", urlPatterns = {"/ontap"})
//public class OnTap extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        int idc =Integer.parseInt(request.getParameter("idc"));
//        int idv =Integer.parseInt(request.getParameter("idv"));
//        String tu = request.getParameter("word");
//        String tu1 = request.getParameter("tu1");
//        String ma = request.getParameter("ma");
//        String[] k = tu1.split("\\s+");
//        String[] o = ma.split("\\s+");
//        String ans = request.getParameter("ans"+idv);
//        CourseDAO courseDAO = new CourseDAO();
//        ProcessDAO processDAO= new ProcessDAO();
//        VocabularyDAO vocabularyDAO = new VocabularyDAO();
//        ArrayList<Vocabulary> list = vocabularyDAO.timTatCaTuMoiBangCourse(idc);
//        int l = idv-1;
//        while(l >= o.length) l-=o.length;
//        if(ans!= null && ans.endsWith("..")){
//            processDAO.updateProcess(user.getId(), Integer.parseInt(o[l]));
//            System.out.println("ok");
//        }
//        else{
//            if(l >= 0){
//                Vocabulary v = vocabularyDAO.timTuMoiBangId(Integer.parseInt(o[l]));
//                if(ans.equals(v.getWord())) processDAO.updateProcess(user.getId(),  Integer.parseInt(o[l]));
//                System.out.println("da");
//            }
//        }
//        DictionaryDAO dictDao = new DictionaryDAO();
//        ArrayList<QuestionVoca> ques = new ArrayList<>();
//        for(Vocabulary x: list){
//            ArrayList<Dictionary> list1 = dictDao.top100Words(x.getGhiChu());
//            Collections.shuffle(list1);
//            for(String s : k){
//                if(x.getWord().equals(s)){
//                    String word = x.getWord();
//                    String nghia = x.getNghiaTV();
//                    ArrayList<String> tmp = new ArrayList<>();
//                    tmp.add(nghia+"..");
//                    for(Dictionary y: list1){
//                        if(x.getGhiChu().equals(y.getPartOfSpeech()) && !y.getMean().equals(nghia)){
//                            tmp.add(y.getMean());
//                        }
//                        if(tmp.size()== 4){
//                            break;
//                        }
//                    }
//                    Collections.shuffle(tmp);
//                    ques.add(new QuestionVoca(word, tmp));
//                    break;
//                }
//            }
//            if(ques.size()== 5){
//                break;
//            }
//        }
//        for(Vocabulary x: list){
//            ArrayList<Dictionary> list1 = dictDao.top100Words(x.getGhiChu());
//            Collections.shuffle(list1);
//            for(String s : k){
//                if(x.getWord().equals(s)){
//                    String word = x.getNghiaTV();
//                    String nghia = x.getWord();
//                    ArrayList<String> tmp = new ArrayList<>();
//                    tmp.add(nghia+"..");
//                    for(Dictionary y: list1){
//                        if(x.getGhiChu().equals(y.getPartOfSpeech()) && !y.getWord().equals(nghia)){
//                            tmp.add(y.getWord());
//                        }
//                        if(tmp.size()== 4){
//                            break;
//                        }
//                    }
//                    Collections.shuffle(tmp);
//                    ques.add(new QuestionVoca(word, tmp));
//                    break;
//                }    
//            }
//            if(ques.size()== 10){
//                break;
//            }   
//        }
//        for(Vocabulary x: list){
//            for(String s : k){
//                if(x.getWord().equals(s)){
//                    String word = x.getNghiaTV();
//                    ArrayList<String> tmp = new ArrayList<>();
//                    ques.add(new QuestionVoca(word, tmp));
//                    break;
//                }
//            }
//            if(ques.size()== 15){
//                break;
//            }   
//        }
////       Collections.shuffle(ques);
//        if(idv == ques.size()){
//            courseDAO.capNhatTienTrinh(idc);
//            Course course =courseDAO.timKhoaHocBangId(idc);
//            request.setAttribute("tong", course.getTongSoTu());
//            request.setAttribute("kq", course.getTienTrinhKH());
//            request.getRequestDispatcher("dapan.jsp").forward(request, response);
//        }
//        else{
//            request.setAttribute("ques", ques.get(idv));
//            request.setAttribute("list", list);
//            request.setAttribute("tu1", tu1);
//            request.setAttribute("ma", ma);
//            request.setAttribute("idv", idv);
//            request.setAttribute("idc", idc);
//            request.setAttribute("o", o);
//            request.getRequestDispatcher("ontap.jsp").forward(request, response);
//        }
//        
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.QuestionVoca;
import model.User;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "OnTap", urlPatterns = {"/ontap"})
public class OnTap extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession(false);
//        User user = (User) session.getAttribute("user");
//        if(user == null)
//        {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        int stt = Integer.parseInt(request.getParameter("stt")+"");
//        int point = Integer.parseInt( request.getParameter("point")+"");
//        ArrayList<QuestionVoca> list = (ArrayList<QuestionVoca>) session.getAttribute("listQuestion");
//        request.setAttribute("point", point);
//        if(stt>=list.size()){
//            request.getRequestDispatcher("hoanthanhontap.jsp").forward(request, response);
//        }
//        QuestionVoca question = list.get(stt);
//        request.setAttribute("stt", stt);
//        request.setAttribute("question",question);
//        if(question.getType()<=2){
//            
//            request.getRequestDispatcher("cauhoiloai1.jsp").forward(request, response);
//        }else{
//            request.getRequestDispatcher("cauhoiloai2.jsp").forward(request, response);
//        }

            request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int stt = Integer.parseInt(request.getParameter("stt")+"");
        int point = Integer.parseInt( request.getParameter("point")+"");
        HttpSession session = request.getSession();
        ArrayList<QuestionVoca> list = (ArrayList<QuestionVoca>) session.getAttribute("listQuestion");
        request.setAttribute("point", point);
        if(stt>=list.size()){
            
            
            request.getRequestDispatcher("hoanthanhontap.jsp").forward(request, response);
            return;
        }
        QuestionVoca question = list.get(stt);
        request.setAttribute("stt", stt);
        request.setAttribute("question",question);
        if(question.getType()<=2){
            
            request.getRequestDispatcher("cauhoiloai1.jsp").forward(request, response);
        }else{
            request.getRequestDispatcher("cauhoiloai2.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

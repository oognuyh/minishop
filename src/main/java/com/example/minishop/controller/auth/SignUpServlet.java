package com.example.minishop.controller.auth;

import com.example.minishop.model.Member;
import com.example.minishop.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/signup.do")
public class SignUpServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Member member = Member.builder()
                .userid(request.getParameter("userid").trim())
                .passwd(request.getParameter("passwd").trim())
                .username(request.getParameter("username").trim())
                .post(request.getParameter("post").trim())
                .addr1(request.getParameter("addr1").trim())
                .addr2(request.getParameter("addr2").trim())
                .phone1(request.getParameter("phone1").trim())
                .phone2(request.getParameter("phone2").trim())
                .phone3(request.getParameter("phone3").trim())
                .email1(request.getParameter("email1").trim())
                .email2(request.getParameter("email2").trim())
                .build();

        boolean isSuccessful = memberService.signUp(member);

        if (isSuccessful) {
            session.setAttribute("member", member);

            response.sendRedirect("home.do");
        } else {
            response.sendRedirect("signup.do");
        }
    }
}

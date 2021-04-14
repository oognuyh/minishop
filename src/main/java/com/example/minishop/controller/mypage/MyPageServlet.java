package com.example.minishop.controller.mypage;

import com.example.minishop.model.Member;
import com.example.minishop.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/mypage.do")
public class MyPageServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Optional<Member> member = Optional.ofNullable((Member) session.getAttribute("member"));
        String destination = member.isPresent() ? "mypage.jsp" : "signin.do";

        System.out.println("MyPageServlet - doGet() : " + destination);

        request.setAttribute("destination", "mypage");
        request.getRequestDispatcher(destination).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        Member member = Member.builder()
                .userid(request.getParameter("userid"))
                .username(request.getParameter("username"))
                .passwd(request.getParameter("passwd"))
                .post(request.getParameter("post"))
                .addr1(request.getParameter("addr1"))
                .addr2(request.getParameter("addr2"))
                .phone1(request.getParameter("phone1"))
                .phone2(request.getParameter("phone2"))
                .phone3(request.getParameter("phone3"))
                .email1(request.getParameter("email1"))
                .email2(request.getParameter("email2"))
                .build();

        System.out.println(member);

        boolean isSuccessful = memberService.updateMember(member);

        System.out.println(isSuccessful);

        if (isSuccessful) {
            session.setAttribute("member", member);
        }

        response.sendRedirect("/mypage.do");
    }
}

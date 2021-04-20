package com.example.minishop.controller.auth;

import com.example.minishop.model.Member;
import com.example.minishop.service.MemberService;
import com.example.minishop.util.Path;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Path.SIGN_UP_VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); 
        HttpSession session = request.getSession();
        Member member = Member.builder()
                .name(request.getParameter("name"))
                .email(request.getParameter("email"))
                .phoneNumber(request.getParameter("phoneNumber"))
                .password(request.getParameter("password"))
                .zip(request.getParameter("zip"))
                .address1(request.getParameter("address1"))
                .address2(request.getParameter("address1"))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Optional<Member> optionalMember = memberService.signUp(member);
        if (optionalMember.isPresent()) {
            session.setAttribute("member", gson.toJson(optionalMember.get()));
            response.sendRedirect(Path.HOME);
        } else {
            response.sendRedirect(Path.SIGN_UP);
        }
    }
}

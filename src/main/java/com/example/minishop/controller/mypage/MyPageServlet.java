package com.example.minishop.controller.mypage;

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
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@WebServlet("/mypage")
public class MyPageServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Optional<Member> optionalMember = Optional.ofNullable((Member) session.getAttribute("member"));

        log.info("{}", optionalMember.orElse(null));

        optionalMember.ifPresent(member -> request.setAttribute("member", gson.toJson(memberService.signIn(member.getEmail(), member.getPassword()).orElse(null), Member.class)));
        request.getRequestDispatcher(optionalMember.isEmpty() ? Path.SIGN_IN_VIEW : Path.MY_PAGE_VIEW).forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Member member = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Member.class);

        log.info("{}", member);

        boolean isSuccessful = memberService.update(member);
        out.print(isSuccessful ? "success" : "failure");
    }
}

package com.example.minishop.controller.auth;

import com.example.minishop.model.Member;
import com.example.minishop.service.MemberService;
import com.example.minishop.util.Path;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(Path.SIGN_IN_VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id"); // phoneNumber or email
        String password = request.getParameter("password"); // password
        String destination = request.getHeader("referer");
        Optional<Member> memberOptional = memberService.signIn(id, password);

        if (memberOptional.isPresent()) {
            session.setAttribute("member", gson.toJson(memberOptional.get(), Member.class));
            response.sendRedirect(destination.equals(request.getRequestURL().toString()) ? Path.HOME : destination);
        } else {
            response.sendRedirect(destination);
        }
    }
}

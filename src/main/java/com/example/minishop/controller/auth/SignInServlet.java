package com.example.minishop.controller.auth;

import com.example.minishop.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/signin.do")
public class SignInServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userid = request.getParameter("userid");
        String passwd = request.getParameter("passwd");
        String destination = Optional.of(request.getParameter("destination")).orElseGet(() -> "home");

        System.out.println(destination);

        memberService.signIn(userid, passwd).ifPresent(member -> session.setAttribute("member", member));

        response.sendRedirect("/" + destination + ".do");
    }
}

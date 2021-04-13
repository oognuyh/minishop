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
        String destination = request.getHeader("referer");
        Optional<Member> member = memberService.signIn(userid, passwd);

        if (member.isPresent()) {
            session.setAttribute("member", member.get());
            response.sendRedirect(destination.equals(request.getRequestURL().toString()) ? "/home.do" : destination);
        } else {
            response.sendRedirect(destination);
        }
    }
}

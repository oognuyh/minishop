package com.example.minishop.controller.auth;

import com.example.minishop.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/checkUserId")
public class UserIdCheckerServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        String userid = request.getParameter("userid");

        boolean isSuccessful = memberService.isDuplicateId(userid);

        if (isSuccessful) {
            out.print("duplicate");
        } else {
            out.print("unique");
        }
    }
}

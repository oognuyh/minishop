package com.example.minishop.controller.auth;

import com.example.minishop.service.MemberService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Slf4j
@WebServlet("/check")
public class DuplicateCheckServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Optional<String> optionalEmail = Optional.ofNullable(request.getParameter("email"));
        Optional<String> optionalPhoneNumber = Optional.ofNullable(request.getParameter("phoneNumber"));

        optionalEmail.ifPresent(email -> out.print(memberService.isDuplicateEmail(email) ? "duplicate" : "unique"));
        optionalPhoneNumber.ifPresent(phoneNumber -> out.print(memberService.isDuplicatePhoneNumber(phoneNumber) ? "duplicate" : "unique"));
        out.flush();
    }
}

package com.example.minishop.controller.order;

import com.example.minishop.model.Cart;
import com.example.minishop.model.Member;
import com.example.minishop.service.CartService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

@WebServlet("/checkout.do")
public class CheckOutServlet extends HttpServlet {
    private final CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Gson gson = new Gson();
        Optional<Member> member = Optional.ofNullable((Member) session.getAttribute("member"));
        if (member.isPresent()) {
            cartService.findCartsByUserId(member.get().getUserid()).ifPresent(carts -> request.setAttribute("carts", gson.toJson(carts)));
            request.setAttribute("member", gson.toJson(member.get()));
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        } else {
            response.sendRedirect("/signin.do");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        List<Cart> carts = gson.fromJson(request.getParameter("carts"), new TypeToken<List<Cart>>(){}.getType());
        PrintWriter out = response.getWriter();

        // carts.forEach(System.out::println);

        HttpSession session = request.getSession();
        session.setAttribute("carts", gson.toJson(carts));

        out.print("success");
    }
}

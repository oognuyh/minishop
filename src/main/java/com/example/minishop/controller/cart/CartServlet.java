package com.example.minishop.controller.cart;

import com.example.minishop.model.Cart;
import com.example.minishop.model.Member;
import com.example.minishop.service.CartService;
import com.example.minishop.util.Path;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private final CartService cartService = new CartService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Optional<Member> optionalMember = Optional.ofNullable((Member) session.getAttribute("member"));
        Type type = new TypeToken<List<Cart>>(){}.getType();

        optionalMember.ifPresent(member -> request.setAttribute("carts",
                gson.toJson(cartService.findCartsByMemberId(member.getId()), type)));
        request.getRequestDispatcher(optionalMember.isPresent() ? Path.CART_VIEW : Path.SIGN_IN_VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Optional<Member> memberOptional = Optional.ofNullable((Member) session.getAttribute("member"));
        if (memberOptional.isEmpty()) {
            out.print("signin");
            return;
        }

        Cart cart = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Cart.class);
        cart.setMemberId(memberOptional.get().getId());

        out.print(cartService.insert(cart) ? "success" : "failure");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Cart cart = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Cart.class);

        out.print(cartService.update(cart) ? "success" : "failure");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Cart cart = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Cart.class);

        out.print(cartService.delete(cart) ? "success" : "failure");
    }
}

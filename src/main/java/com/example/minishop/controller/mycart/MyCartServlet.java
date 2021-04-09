package com.example.minishop.controller.mycart;

import com.example.minishop.model.Cart;
import com.example.minishop.model.Member;
import com.example.minishop.service.CartService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/mycart.do")
public class MyCartServlet extends HttpServlet {
    private final CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Optional<Member> member = Optional.ofNullable((Member) session.getAttribute("member"));
        Gson gson = new Gson();

        member.flatMap(m -> cartService.findCartsByUserId(m.getUserid())).ifPresent(carts -> request.setAttribute("carts", gson.toJson(carts)));
        request.getRequestDispatcher(member.isPresent() ? "mycart.jsp" : "signin.do").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        Optional<Member> member = Optional.ofNullable((Member) session.getAttribute("member"));
        Cart cart = gson.fromJson(request.getParameter("cart"), Cart.class);
        String action = request.getParameter("action");
        String message = "";

        member.ifPresent(m -> cart.setUserid(m.getUserid()));
        if (member.isEmpty()) {
            out.print("signin");
            return;
        }

        System.out.println("MyCartServlet.doPost() - cart: " + cart);

        switch (action) {
            case "add":
                message = cartService.insertCart(cart) ? "success" : "failure";
                break;
            case "update":
                message = cartService.updateGAmountByNum(cart.getNum(), cart.getGAmount()) ? "success" : "failure";
                break;
            case "delete":
                message = cartService.deleteCartByNum(cart.getNum()) ? "success" : "failure";
                break;
        }

        out.print(message);
    }
}

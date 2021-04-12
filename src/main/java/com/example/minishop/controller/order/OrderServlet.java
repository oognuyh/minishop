package com.example.minishop.controller.order;

import com.example.minishop.model.Member;
import com.example.minishop.model.Order;
import com.example.minishop.service.CartService;
import com.example.minishop.service.OrderService;
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
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@WebServlet("/order.do")
public class OrderServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();
    private final CartService cartService = new CartService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Gson gson = new Gson();
        Optional<Member> member = Optional.ofNullable((Member) session.getAttribute("member"));

        if (member.isPresent()) {
            request.setAttribute("orders", gson.toJson(orderService.findOrdersByUserId(member.get().getUserid())));
            request.getRequestDispatcher("/order.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/signin.do").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        Type type = new TypeToken<List<Order>>(){}.getType();
        List<Order> orders = gson.fromJson(request.getParameter("orders"), type);

        orders.forEach(order -> {
            if (order.getNum() != 0)
                cartService.deleteCartByNum(order.getNum());
            orderService.insertOrder(order);
        });

        out.print("Successfully ordered");
    }
}

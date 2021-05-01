package com.example.minishop.controller.order;

import com.example.minishop.model.Member;
import com.example.minishop.model.Order;
import com.example.minishop.service.OrderService;
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
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Optional<Member> optionalMember = Optional.ofNullable((Member) session.getAttribute("member"));
        if (optionalMember.isEmpty()) request.getRequestDispatcher(Path.SIGN_IN_VIEW).forward(request, response);
        else {
            Type type = new TypeToken<List<Order>>(){}.getType();

            log.error("{}", orderService.findOrderByMemberId(optionalMember.get().getId()));
            
            request.setAttribute("orders", gson.toJson(orderService.findOrderByMemberId(optionalMember.get().getId()), type));
            request.getRequestDispatcher(Path.ORDER_VIEW).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), Order.class);
        PrintWriter out = response.getWriter();

        session.removeAttribute("order");

        log.info("{}", order);

        orderService.insert(order);

        out.print("success");
    }
}

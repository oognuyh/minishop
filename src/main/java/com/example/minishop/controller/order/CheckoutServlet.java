package com.example.minishop.controller.order;

import com.example.minishop.model.Member;
import com.example.minishop.model.Order;
import com.example.minishop.model.OrderDetail;
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
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Optional<Member> optionalMember = Optional.ofNullable((Member) session.getAttribute("member"));
        if (optionalMember.isEmpty()) {
            response.sendRedirect(Path.SIGN_IN);
        } else {
            Order order = (Order) session.getAttribute("order");
            Type type = new TypeToken<Order>(){}.getType();

            log.info("{}", order);

            request.setAttribute("order", gson.toJson(order, type));
            request.getRequestDispatcher(Path.CHECKOUT_VIEW).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        Optional<Member> optionalMember = Optional.ofNullable((Member) session.getAttribute("member"));
        Type type = new TypeToken<List<OrderDetail>>(){}.getType();
        List<OrderDetail> orderDetails = gson.fromJson(request.getReader().lines().collect(Collectors.joining()), type);
        orderDetails.forEach(orderDetail -> orderDetail.setTotalPrice(orderDetail.getProductQuantity() * orderDetail.getProduct().getPrice()));

        log.info("{}", orderDetails);

        if (optionalMember.isEmpty()) {
            out.print("signin");
        } else {
            Order order = Order.builder()
                    .memberId(optionalMember.get().getId())
                    .billingName(optionalMember.get().getName())
                    .billingPhoneNumber(optionalMember.get().getPhoneNumber())
                    .billingZip(optionalMember.get().getZip())
                    .billingAddress1(optionalMember.get().getAddress1())
                    .billingAddress2(optionalMember.get().getAddress2())
                    .shippingName(optionalMember.get().getName())
                    .shippingPhoneNumber(optionalMember.get().getPhoneNumber())
                    .shippingZip(optionalMember.get().getZip())
                    .shippingAddress1(optionalMember.get().getAddress1())
                    .shippingAddress2(optionalMember.get().getAddress2())
                    .orderDetails(orderDetails)
                    .totalPrice(orderDetails.stream().map(OrderDetail::getTotalPrice)
                            .reduce(Integer::sum).orElse(0))
                    .paymentMethod("credit")
                    .build();

            session.setAttribute("order", order);
            out.print("success");
        }
    }
}

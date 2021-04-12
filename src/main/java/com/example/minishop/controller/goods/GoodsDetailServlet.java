package com.example.minishop.controller.goods;

import com.example.minishop.service.GoodsService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods/detail")
public class GoodsDetailServlet extends HttpServlet {
    private final GoodsService goodsService = new GoodsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        String gCode = request.getParameter("gCode");

        goodsService.findGoodsByGCode(gCode).ifPresent(goods -> request.setAttribute("goods", gson.toJson(goods)));

        request.getRequestDispatcher("/goodsdetail.jsp").forward(request, response);
    }
}

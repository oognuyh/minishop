package com.example.minishop.controller.goods;

import com.example.minishop.service.GoodsService;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/goods.do")
public class GoodsServlet extends HttpServlet {
    private final GoodsService goodsService = new GoodsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Gson gson = new Gson();
        String gCategory = request.getParameter("gCategory");

        goodsService.findGoodsByGCategory(gCategory).ifPresent(goods -> request.setAttribute("goods", gson.toJson(goods)));

        request.getRequestDispatcher("goods.jsp").forward(request, response);
    }
}

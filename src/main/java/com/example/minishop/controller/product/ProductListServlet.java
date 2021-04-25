package com.example.minishop.controller.product;

import com.example.minishop.model.Product;
import com.example.minishop.service.ProductService;
import com.example.minishop.util.Path;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet("/product/list")
public class ProductListServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));

        List<Product> products = productService.findProductsByCategoryId(categoryId);

        log.info("products: {}", products);

        request.setAttribute("products", gson.toJson(products));
        request.getRequestDispatcher(Path.PRODUCT_List_VIEW).forward(request, response);
    }
}

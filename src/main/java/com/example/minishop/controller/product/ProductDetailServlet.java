package com.example.minishop.controller.product;

import com.example.minishop.model.Product;
import com.example.minishop.service.ProductService;
import com.example.minishop.util.Path;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/product/detail")
public class ProductDetailServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        Product product = productService.findProductById(productId);

        System.out.println("ProductDetailServlet.doGet() - " + product);

        request.setAttribute("product", gson.toJson(product));
        request.getRequestDispatcher(Path.PRODUCT_DETAIL_VIEW).forward(request, response);
    }
}

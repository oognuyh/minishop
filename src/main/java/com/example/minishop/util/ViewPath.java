package com.example.minishop.util;

public class ViewPath {
    private static final String prefix = "/WEB-INF/views/";
    private static final String suffix = ".jsp";

    public static String SIGN_UP = prefix + "signup" + suffix;
    public static String SIGN_IN = prefix + "signin" + suffix;
    public static String GOODS = prefix + "goods" + suffix;
    public static String GOODS_DETAIL = prefix + "goodsdetail" + suffix;
    public static String MY_CART = prefix + "cart" + suffix;
    public static String MY_ORDER = prefix + "order" + suffix;
}

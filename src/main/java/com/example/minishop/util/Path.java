package com.example.minishop.util;

public class Path {
    private static final String prefix = "/WEB-INF/views";
    private static final String suffix = ".jsp";

    public static final String HOME = "/home";

    public static final String SIGN_IN = "/signin";
    public static final String SIGN_UP = "/signup";
    public static final String SIGN_OUT = "/signout";

    public static final String PRODUCT_LIST = "/product/list";
    public static final String PRODUCT_DETAIL = "/product/detail";

    public static final String CART = "/cart";

    public static final String CHECKOUT = "/checkout";
    public static final String ORDER = "/order";

    public static final String MY_PAGE = "/mypage";

    public static final String SIGN_IN_VIEW = prefix + "/auth/signin" + suffix;
    public static final String SIGN_UP_VIEW  = prefix + "/auth/signup" + suffix;
    public static final String SIGN_OUT_VIEW  = prefix + "/auth/signout" + suffix;

    public static final String PRODUCT_List_VIEW  = prefix + "/product/list" + suffix;
    public static final String PRODUCT_DETAIL_VIEW  = prefix + "/product/detail" + suffix;

    public static final String CART_VIEW  = prefix + "/cart/cart" + suffix;

    public static final String CHECKOUT_VIEW  =prefix + "/order/checkout" + suffix;
    public static final String ORDER_VIEW  = prefix + "/order/order" + suffix;

    public static final String MY_PAGE_VIEW  = prefix + "/mypage/mypage" + suffix;
}

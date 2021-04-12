package com.example.minishop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    int num;
    String userid;
    String gCode;
    String gName;
    int gPrice;
    String gSize;
    String gColor;
    int gAmount;
    String gImage;
    String orderName;
    String post;
    String addr1;
    String addr2;
    String phone;
    String payMethod;
    String orderday;
}

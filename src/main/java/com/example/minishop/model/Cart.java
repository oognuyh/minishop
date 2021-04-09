package com.example.minishop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart {
    private int num;
    private String userid;
    private String gCode;
    private String gName;
    private int gPrice;
    private String gSize;
    private String gColor;
    private int gAmount;
    private String gImage;
}

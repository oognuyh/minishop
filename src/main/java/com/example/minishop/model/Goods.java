package com.example.minishop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goods {
    private String gCode;
    private String gCategory;
    private String gName;
    private String gContent;
    private int gPrice;
    private String gImage;
}

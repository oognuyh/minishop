package com.example.minishop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Goods {
    private String gCode;
    private String gCategory;
    private String gName;
    private String gContent;
    private int gPrice;
    private String gImage;
}

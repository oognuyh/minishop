package com.example.minishop.config;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OracleProperties {
    private String driver;
    private String url;
    private String username;
    private String password;
}

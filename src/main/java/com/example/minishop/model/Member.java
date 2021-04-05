package com.example.minishop.model;

import lombok.*;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Member {
    private String userid;
    private String username;
    private String passwd;
    private String post;
    private String addr1;
    private String addr2;
    private String phone1;
    private String phone2;
    private String phone3;
    private String email1;
    private String email2;
}

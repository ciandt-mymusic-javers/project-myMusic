package com.ciandt.summit.bootcamp2022.dto.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateAuthorizerRequestData {

    private String name;

    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

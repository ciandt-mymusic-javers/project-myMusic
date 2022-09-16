package com.ciandt.summit.bootcamp2022.entity;

public enum ERole {
    BASIC("Comum"),
    PREMIUM("Premium");

    private final String role;

    ERole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}

package com.example.artgallery.DTO;

public class LoginRequest {

    private String senha;
    private String login;

    public LoginRequest() {
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
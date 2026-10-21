package com.example.artgallery.DTO;

import javax.print.DocFlavor;

public class LoginResponse {
    public LoginResponse() {
    }

    private String mensagem;

    private String tiken;

    public String getTiken() {
        return tiken;
    }

    public void setToken(String token) {
        this.tiken = tiken;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
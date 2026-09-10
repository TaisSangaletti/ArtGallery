package com.example.artgallery.DTO;

public class LoginResponse {

    private String mensagem;

    public LoginResponse() {
    }

    public LoginResponse(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
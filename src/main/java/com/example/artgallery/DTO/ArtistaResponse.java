package com.example.artgallery.DTO;

public class ArtistaResponse {

    private Long id;
    private String mensagem;

    public ArtistaResponse() {
    }

    public ArtistaResponse(Long id, String mensagem) {
        this.id = id;
        this.mensagem = mensagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
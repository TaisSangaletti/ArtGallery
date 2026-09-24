package com.example.artgallery.DTO;

public class EmpresaResponse {
    public EmpresaResponse() {
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

    private Long id;
    private String mensagem;

}

package com.example.artgallery.DTO;

public class ExposicaoResponse {

    private Long id;
    private String nome;
    private String local;
    private String dataInicio;
    private String dataFim;
    private String mensagem;

    public ExposicaoResponse(Long id, String mensagem) {
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
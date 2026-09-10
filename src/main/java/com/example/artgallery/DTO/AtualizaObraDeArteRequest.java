package com.example.artgallery.DTO;

public class AtualizaObraDeArteRequest {

    public AtualizaObraDeArteRequest() {
    }

    private String titulo;
    private String tecnica;
    private String anoCriacao;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTecnica() {
        return tecnica;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public String getAnoCriacao() {
        return anoCriacao;
    }

    public void setAnoCriacao(String anoCriacao) {
        this.anoCriacao = anoCriacao;
    }
}


package com.example.artgallery.DTO;

public class ObraDeArteRequest {

    private String titulo;
    private String tecnica;
    private String anoCriacao;

    public ObraDeArteRequest() {
    }

    public ObraDeArteRequest(String titulo, String tecnica, String anoCriacao) {
        this.titulo = titulo;
        this.tecnica = tecnica;
        this.anoCriacao = anoCriacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTecnica() {
        return tecnica;
    }

    public String getAnoCriacao() {
        return anoCriacao;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setTecnica(String tecnica) {
        this.tecnica = tecnica;
    }

    public void setAnoCriacao(String anoCriacao) {
        this.anoCriacao = anoCriacao;
    }
}

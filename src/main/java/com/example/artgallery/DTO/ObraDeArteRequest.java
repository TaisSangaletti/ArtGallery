package com.example.artgallery.DTO;

import java.math.BigDecimal;

public class ObraDeArteRequest {

    private String titulo;
    private String tecnica;
    private String anoCriacao;
    private BigDecimal valorSeguro;

    public ObraDeArteRequest() {
    }

    public ObraDeArteRequest(
            String titulo,
            String tecnica,
            String anoCriacao,
            BigDecimal valorSeguro) {

        this.titulo = titulo;
        this.tecnica = tecnica;
        this.anoCriacao = anoCriacao;
        this.valorSeguro = valorSeguro;
    }

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

    public BigDecimal getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(BigDecimal valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
}
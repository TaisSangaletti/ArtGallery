package com.example.artgallery.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal; // Importante para valores monetários

@Entity
public class ObraDeArte {

    public ObraDeArte() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String tecnica;
    private String anoCriacao;
    private BigDecimal valorSeguro; // <-- Novo campo adicionado

    public ObraDeArte(Long id, String titulo, String tecnica, String anoCriacao, BigDecimal valorSeguro) {
        this.id = id;
        this.titulo = titulo;
        this.tecnica = tecnica;
        this.anoCriacao = anoCriacao;
        this.valorSeguro = valorSeguro;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTecnica() { return tecnica; }
    public void setTecnica(String tecnica) { this.tecnica = tecnica; }

    public String getAnoCriacao() { return anoCriacao; }
    public void setAnoCriacao(String anoCriacao) { this.anoCriacao = anoCriacao; }

    public BigDecimal getValorSeguro() { return valorSeguro; }
    public void setValorSeguro(BigDecimal valorSeguro) { this.valorSeguro = valorSeguro; }
}
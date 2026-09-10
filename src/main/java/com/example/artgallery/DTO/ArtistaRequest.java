package com.example.artgallery.DTO;

public class ArtistaRequest {

    private String nome;
    private String cpf;
    private String nacionalidade;


    public ArtistaRequest() {
    }

    public ArtistaRequest(String nome, String cpf, String nacionalidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.nacionalidade = nacionalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
}
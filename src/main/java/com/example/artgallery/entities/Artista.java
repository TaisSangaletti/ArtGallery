package com.example.artgallery.entities;

public class Artista {

    private String nome;
    private String cpf;
    private String nacionalidade;
    private Long id;

    public Artista(Long id, String nome, String cpf, String nacionalidade){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.nacionalidade = nacionalidade;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNacionalidade (){
        return this.nacionalidade;
    }

    public void setnacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

}
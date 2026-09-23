package com.example.artgallery.DTO;

public class AtualizaArtistaRequest {

    private String nome;
    private String cpf;
    private String nacionalidade;


    public AtualizaArtistaRequest() {
    }

    public AtualizaArtistaRequest(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
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

        public String getNacionalidade () {
            return nacionalidade;
        }

        public void setNacionalidade (String nacionalidade){
            this.nacionalidade = nacionalidade;

        }
    }
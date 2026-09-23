package com.example.artgallery.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


public class ArtistaRequest {




    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(
            regexp = "\\d{11}",
            message = "O CPF deve ter exatamente 11 dígitos."
    )
    private String cpf;

    @NotBlank(message = "A nacionalidade é obrigatória.")
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
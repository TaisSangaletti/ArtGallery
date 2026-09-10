package com.example.artgallery.DTO;

public class AtualizaExposicaoRequest {

    public AtualizaExposicaoRequest() {
    }

    private String nome;
    private String local;
    private String dataInicio;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }
}

package com.example.artgallery.DTO;

public class ExposicaoRequest {

    private String nome;
    private String local;
    private String dataInicio;
    private String dataFim;

    public ExposicaoRequest() {
    }

    public ExposicaoRequest(String nome, String local, String dataInicio, String dataFim) {
        this.nome = nome;
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }
}

package com.example.artgallery.DTO;

import java.util.List;

public class ExposicaoRequest {

    private String nome;
    private String local;
    private String dataInicio;
    private String dataFim;

    private List<Long> obrasIds;

    public ExposicaoRequest() {
    }

    public ExposicaoRequest(
            String nome,
            String local,
            String dataInicio,
            String dataFim,
            List<Long> obrasIds) {

        this.nome = nome;
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.obrasIds = obrasIds;
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

    public List<Long> getObrasIds() {
        return obrasIds;
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

    public void setObrasIds(List<Long> obrasIds) {
        this.obrasIds = obrasIds;
    }
}
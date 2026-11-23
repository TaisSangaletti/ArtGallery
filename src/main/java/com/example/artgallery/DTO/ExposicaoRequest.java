package com.example.artgallery.DTO;

import java.time.LocalDate;
import java.util.List;

public class ExposicaoRequest {

    private String nome;
    private String local;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<Long> obraIds;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public List<Long> getObraIds() { return obraIds; }
    public void setObraIds(List<Long> obraIds) { this.obraIds = obraIds; }
}
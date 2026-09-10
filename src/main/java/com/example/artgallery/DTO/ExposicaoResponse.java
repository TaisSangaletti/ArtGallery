package com.example.artgallery.DTO;

public class ExposicaoResponse {

    private Long id;
    private String nome;
    private String local;
    private String dataInicio;
    private String dataFim;

    public ExposicaoResponse() {
    }

    public ExposicaoResponse(Long id, String nome, String local,
                             String dataInicio, String dataFim) {
        this.id = id;
        this.nome = nome;
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
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
}

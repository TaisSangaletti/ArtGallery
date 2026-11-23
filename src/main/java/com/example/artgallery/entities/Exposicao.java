package com.example.artgallery.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exposicao {

    public Exposicao() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String local;

    private String dataInicio;

    private String dataFim;

    @ManyToMany
    @JoinTable(
            name = "exposicao_obra",
            joinColumns = @JoinColumn(name = "exposicao_id"),
            inverseJoinColumns = @JoinColumn(name = "obra_id")
    )
    private List<ObraDeArte> obras = new ArrayList<>();

    public Exposicao(Long id,
                     String nome,
                     String local,
                     String dataInicio,
                     String dataFim,
                     List<ObraDeArte> obras) {

        this.id = id;
        this.nome = nome;
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.obras = obras;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public List<ObraDeArte> getObras() {
        return obras;
    }

    public void setObras(List<ObraDeArte> obras) {
        this.obras = obras;
    }
}
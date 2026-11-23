package com.example.artgallery.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToMany
    @JoinTable(
            name = "exposicao_obra",
            joinColumns = @JoinColumn(name = "exposicao_id"),
            inverseJoinColumns = @JoinColumn(name = "obra_id")
    )
    private List<ObraDeArte> obras = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public List<ObraDeArte> getObras() { return obras; }
    public void setObras(List<ObraDeArte> obras) { this.obras = obras; }
}
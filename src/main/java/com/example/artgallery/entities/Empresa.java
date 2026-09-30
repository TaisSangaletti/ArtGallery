package com.example.artgallery.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Empresa {

    public Empresa(){
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String razaoSocial;
    private String nomeFantasia;
    @Column(unique = true)
    private String cnpj;
    @Column(unique = true)
    private String inscricaoEstadual;

    @OneToMany(mappedBy = "empresa")
    private List<Usuario> usuarios;

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;


    }

}

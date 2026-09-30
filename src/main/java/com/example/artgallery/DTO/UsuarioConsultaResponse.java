package com.example.artgallery.DTO;

import com.example.artgallery.entities.Usuario;

public class UsuarioConsultaResponse {

    private String nome;
    private String cpf;
    private String dataNascimento;
    private Long empresa_id;
    private String razaoSocialEmpresa;
    private Long id;


        public UsuarioConsultaResponse(){

        }

        public UsuarioConsultaResponse(Usuario usuario){
            this.cpf = usuario.getCpf();
            this.nome = usuario.getNome();
            this.dataNascimento = usuario.getDataNascimento();
            this.id = usuario.getId();
            if (usuario.getEmpresa()!= null) {
                this.empresa_id = usuario.getEmpresa().getId();
                this.razaoSocialEmpresa = usuario.getEmpresa().getRazaoSocial();
            }
    }

    public String getNome() {
        return nome;
    }

    public String getRazaoSocialEmpresa() {
        return razaoSocialEmpresa;
    }

    public void setRazaoSocialEmpresa(String razaoSocialEmpresa) {
        this.razaoSocialEmpresa = razaoSocialEmpresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(Long empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

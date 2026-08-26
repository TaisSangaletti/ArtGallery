package com.example.artgallery.entities;

import javax.xml.crypto.Data;

public class Usuario {

    private String nome;
    private String cpf;
    private String dataNascimento;
    private Long id;
    public Long getId(){
        return this.id;
    }

    public Usuario(){
    this.id = 1L;

}

public Usuario (String nome, String cpf, String dataNascimento){
        this.id = 1000L;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;


}

    public String getNome() {
        return this.nome;
    }

        public void setNome(String nome) {
            if (this.nome.isEmpty()) {

                this.nome = nome;
            }
        }
            public String getcpf() {
                return this.cpf;
            }
            public void setcpf(String cpf) {
                this.cpf = cpf;
            }
                public String getdataNascimento() {
                    return this.dataNascimento;
                }
                public void setDataNascimento(String dataNascimento) {
                    this.dataNascimento = dataNascimento;


    }
}

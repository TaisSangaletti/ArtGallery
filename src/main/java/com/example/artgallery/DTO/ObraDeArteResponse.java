/**
*
*/
package com.example.artgallery.DTO;

public class ObraDeArteResponse {

    private Long id;
    private String titulo;
    private String tecnica;
    private String anoCriacao;

    public ObraDeArteResponse() {
    }

    public ObraDeArteResponse(Long id, String titulo,
                              String tecnica, String anoCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.tecnica = tecnica;
        this.anoCriacao = anoCriacao;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTecnica() {
        return tecnica;
    }

    public String getAnoCriacao() {
        return anoCriacao;
    }
}

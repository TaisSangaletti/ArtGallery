/**
*
*/
package com.example.artgallery.DTO;

public class ObraDeArteResponse {

    private Long id;
    private String titulo;
    private String tecnica;
    private String anoCriacao;
    private String mensagem;

    public ObraDeArteResponse(Long id, String mensagem) {
        this.id = id;
        this.mensagem = mensagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
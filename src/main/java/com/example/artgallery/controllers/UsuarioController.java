package com.example.artgallery.controllers;

import com.example.artgallery.entities.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @GetMapping
    public String ConsultaUsuario (){
        return "Hello World!";
    }

    @GetMapping("/{id}")
    public Usuario ConsultaUsuarioPorId(@PathVariable Long id){

        Usuario usuario = new Usuario();

        usuario.setId(id);
        usuario.setNome("Tais");
        usuario.setCpf("10114979995");
        usuario.setDataNascimento("20091994");
        return usuario;

    }

    @GetMapping("/empresa/{empresaId}")
    public Usuario ConsultaUsuarioPorEmpresa(@PathVariable Long empresaId){
    Usuario usuarioConstructorCompleto = new Usuario (empresaId, "Tais", "10114979995", "20091994");
        return usuarioConstructorCompleto;
    }



    @PostMapping
public ResponseEntity<Usuario> CadastrarUsuario(@RequestBody Usuario usuarioRequest){
        return ResponseEntity.ok(usuarioRequest);



}

}

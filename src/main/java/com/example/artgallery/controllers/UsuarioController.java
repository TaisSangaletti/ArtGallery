package com.example.artgallery.controllers;

import com.example.artgallery.entities.Usuario;
import com.example.artgallery.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

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

        //Salvando no banco
        usuarioRepository.save(usuarioBanco);
        return ResponseEntity.ok(usuarioRequest);



}

}

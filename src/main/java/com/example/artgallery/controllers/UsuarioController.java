package com.example.artgallery.controllers;

import com.example.artgallery.DTO.AtualizaStatusUsuarioRequest;
import com.example.artgallery.DTO.UsuarioConsultaResponse;
import com.example.artgallery.DTO.UsuarioRequest;
import com.example.artgallery.DTO.UsuarioResponse;
import com.example.artgallery.entities.Usuario;
import com.example.artgallery.repository.UsuarioRepository;
import com.example.artgallery.repository.EmpresaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuário Controller", description = "Classe responsável por fazer o controle do CRUD de Usuários!")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;


    @GetMapping
    @Operation(summary = "Listar Todos os Usuários", description = "Método que consulta e retorna uma lista com todos os usuários cadastrados no banco, convertendo cada registro para o formato de DTO.")
    public List<UsuarioConsultaResponse> ConsultaUsuario(){

        return usuarioRepository.findAll().stream().map(UsuarioConsultaResponse::new).toList();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Buscar Usuário por ID", description = "Método de consulta de usuário por ID que retorna somente um usário e recebe como parâmetro o Path Variable ID e retorna como saída o objeto do DTO")

    public ResponseEntity<UsuarioConsultaResponse> ConsultaUsuarioPorId(@PathVariable Long id){
        var usuario = usuarioRepository.findById(id).orElse(null);

        if(usuario == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new UsuarioConsultaResponse(usuario));
    }

    @GetMapping("/empresa/{empresaId}")
    @Operation(summary = "Consultar Usuário por ID da Empresa", description = "Método que consulta e retorna um usuário associado ao ID da empresa fornecido como Path Variable na URL.")
    public Usuario consultaUsuarioPorEmpresa(@PathVariable Long empresaId) {
        Usuario usuarioConstructorCompleto =
                new Usuario(empresaId, "Tais", "10114979995", "20091994");

        return usuarioConstructorCompleto;
    }

    @PostMapping
    @Operation(summary = "Cadastrar Novo Usuário", description = "Método que realiza o cadastro de um novo usuário, validando a existência da empresa informada, definindo a data de cadastro e o status inicial, e salvando o registro no banco.")
    public ResponseEntity<UsuarioResponse> cadastrarUsuario(
            @RequestBody UsuarioRequest usuarioRequest) {

        var empresaBanco = empresaRepository.findById(usuarioRequest.getEmpresa_id()).orElse(null);

        if(empresaBanco == null) {
            return ResponseEntity.notFound().build();
        }

        Usuario usuarioBanco = new Usuario();
        usuarioBanco.setNome(usuarioRequest.getNome());
        usuarioBanco.setCpf(usuarioRequest.getCpf());
        usuarioBanco.setDataNascimento(usuarioRequest.getDataNascimento());
        usuarioBanco.setSenha(usuarioRequest.getSenha());
        usuarioBanco.setEmpresa(empresaBanco);

        usuarioBanco.setDataCadastro(LocalDateTime.now());
        usuarioBanco.setStatus("A");

        //salvando no banco
        usuarioRepository.save(usuarioBanco);

        return ResponseEntity.ok(new UsuarioResponse(usuarioBanco.getId(),
                "Cadastro com sucesso!"));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Usuário por ID", description = "Método que atualiza os dados de um usuário existente localizado pelo ID na URL, validando também a empresa informada e salvando as alterações no banco.")

    public ResponseEntity<UsuarioResponse> atualizarUsuario(
                @PathVariable Long id,
                @RequestBody UsuarioRequest usuarioRequest) {
        //Consulta no banco
        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);

        if(usuarioBanco != null){

            var empresaBanco = empresaRepository.findById(usuarioRequest.getEmpresa_id()).orElse(null);
            if(empresaBanco == null){
                return ResponseEntity.notFound().build();
            }
            usuarioBanco.setEmpresa(empresaBanco);
            usuarioBanco.setNome(usuarioRequest.getNome());
            usuarioBanco.setCpf(usuarioRequest.getCpf());
            usuarioBanco.setDataNascimento(usuarioRequest.getDataNascimento());
            usuarioBanco.setDataAtualizacao(LocalDateTime.now());
            usuarioBanco.setSenha(usuarioRequest.getSenha());
            usuarioRepository.save(usuarioBanco);

            return ResponseEntity.ok(new UsuarioResponse(usuarioBanco.getId(),
                    "UsuarioAtualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualizar Status do Usuário por ID", description = "Método que realiza a atualização parcial do status de um usuário localizado pelo ID na URL, recebendo o novo status via DTO e salvando a alteração no banco.")
    public ResponseEntity<UsuarioResponse> atualizaStatus(
                @PathVariable Long id,
                @RequestBody AtualizaStatusUsuarioRequest usuarioRequest){
        //Consulta no banco
        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);

        if(usuarioBanco != null){
            usuarioBanco.setStatus(usuarioRequest.getStatus());
            usuarioRepository.save(usuarioBanco);

            return ResponseEntity.ok(new UsuarioResponse(usuarioBanco.getId(),
                    "Status atualizado com sucesso!"));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Usuários por ID", description = "Método que realiza a exclusão lógica (inativação) de um usuário localizado pelo ID na URL, alterando seu status para desativado e salvando a alteração no banco.")

    public ResponseEntity<UsuarioResponse> deletar(@PathVariable Long id){
        //Consulta no banco


        Usuario usuarioBanco = usuarioRepository.findById(id).orElse(null);

        if(usuarioBanco != null){
            usuarioBanco.setStatus("D");
            usuarioRepository.save(usuarioBanco);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }




}
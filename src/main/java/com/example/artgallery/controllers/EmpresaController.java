package com.example.artgallery.controllers;


import com.example.artgallery.DTO.EmpresaCosultaResponse;
import com.example.artgallery.DTO.EmpresaRequest;
import com.example.artgallery.DTO.EmpresaResponse;
import com.example.artgallery.DTO.UsuarioConsultaResponse;
import com.example.artgallery.entities.Empresa;
import com.example.artgallery.repository.EmpresaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@Tag(name = "Empresas Controller", description = "Classe responsável por fazer o controle do CRUD das empresas!")

public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;


    @GetMapping
    @Operation(summary = "Listar Todas as Empresas", description = "Método que consulta e retorna uma lista com todas as empresas cadastradas no banco, convertendo cada registro para o formato de DTO.")
    public List<EmpresaCosultaResponse> listarTodos() {

        return empresaRepository.findAll()
        .stream()
        .map(EmpresaCosultaResponse::new).toList();
    }




    @PostMapping("/criar")
    @Operation(summary = "Cadastrar Nova Empresa", description = "Método que realiza o cadastro de uma nova empresa, preenchendo seus dados com base no DTO recebido e salvando o novo registro no banco.")
    public ResponseEntity<EmpresaResponse> cadastrarEmpresa(@RequestBody EmpresaRequest empresaRequest) {

        Empresa empresaBanco = new Empresa();

        empresaBanco.setRazaoSocial(empresaRequest.getRazaoSocial());
        empresaBanco.setCnpj(empresaRequest.getCnpj());
        empresaBanco.setNomeFantasia(empresaRequest.getNomeFantasia());
        empresaBanco.setInscricaoEstadual(empresaRequest.getInscricaoEstadual());


        empresaRepository.save(empresaBanco);

        EmpresaResponse empresaResponse = new EmpresaResponse();

        empresaResponse.setId(empresaBanco.getId());
        empresaResponse.setMensagem("Cadastro da empresa realizado com sucesso");

        return ResponseEntity.ok(empresaResponse);
    }

    @GetMapping("/cnpj/{cnpj}/usuarios")
    @Operation(summary = "Buscar Usuários por CNPJ da Empresa", description = "Método que consulta e retorna uma lista de usuários vinculados a uma empresa localizada pelo CNPJ fornecido na URL.")
    public ResponseEntity<List<UsuarioConsultaResponse>> buscarUsuariosPorCnpjEmpresa(@PathVariable String cnpj) {

        var empresaBanco = empresaRepository.getEmpresaByCnpj(cnpj).orElse(null);

        if (empresaBanco == null)
            return ResponseEntity.notFound().build();

        var usuarioEmpresaBanco = empresaBanco.getUsuarios()
                .stream()
                .map(UsuarioConsultaResponse::new)
                .toList();

        return ResponseEntity.ok(usuarioEmpresaBanco);

    }
}

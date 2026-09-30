package com.example.artgallery.controllers;


import com.example.artgallery.DTO.EmpresaCosultaResponse;
import com.example.artgallery.DTO.EmpresaRequest;
import com.example.artgallery.DTO.EmpresaResponse;
import com.example.artgallery.DTO.UsuarioConsultaResponse;
import com.example.artgallery.entities.Empresa;
import com.example.artgallery.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;


    @GetMapping
    public List<EmpresaCosultaResponse> listarTodos() {

        return empresaRepository.findAll()
        .stream()
        .map(EmpresaCosultaResponse::new).toList();
    }




    @PostMapping("/criar")
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

package com.example.artgallery.controllers;


import com.example.artgallery.DTO.EmpresaRequest;
import com.example.artgallery.DTO.EmpresaResponse;
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
    public List<Empresa> listarTodos(){

        return empresaRepository.findAll();
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
}

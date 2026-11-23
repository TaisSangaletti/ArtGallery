package com.example.artgallery.controllers;

import com.example.artgallery.DTO.ExposicaoRequest;
import com.example.artgallery.DTO.ExposicaoResponse;
import com.example.artgallery.entities.Exposicao;
import com.example.artgallery.repository.ExposicaoRepository;
import com.example.artgallery.repository.ObraDeArteRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.artgallery.entities.ObraDeArte;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/exposicoes")
@Tag(
        name = "Exposição Controller",
        description = "Classe responsável por fazer o controle do CRUD de Exposições!"
)
public class ExposicaoController {

    private final ExposicaoRepository exposicaoRepository;
    private final ObraDeArteRepository obraDeArteRepository;

    public ExposicaoController(
            ExposicaoRepository exposicaoRepository,
            ObraDeArteRepository obraDeArteRepository) {

        this.exposicaoRepository = exposicaoRepository;
        this.obraDeArteRepository = obraDeArteRepository;
    }

    @GetMapping
    @Operation(
            summary = "Listar Todas as Exposições",
            description = "Método que consulta e retorna uma lista com todas as exposições cadastradas no banco."
    )
    public List<Exposicao> getExposicoes() {
        return exposicaoRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar Exposição por ID",
            description = "Método que consulta e retorna uma exposição localizada pelo ID."
    )
    public ResponseEntity<Exposicao> exposicaoPorId(@PathVariable Long id) {

        return exposicaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar Nova Exposição",
            description = "Método que realiza o cadastro de uma nova exposição."
    )
    public ResponseEntity<ExposicaoResponse> cadastrarExposicao(
            @RequestBody ExposicaoRequest request) {

        Exposicao exposicao = new Exposicao();

        exposicao.setNome(request.getNome());
        exposicao.setLocal(request.getLocal());
        exposicao.setDataInicio(request.getDataInicio());
        exposicao.setDataFim(request.getDataFim());

        exposicao = exposicaoRepository.save(exposicao);

        return ResponseEntity.ok(
                new ExposicaoResponse(
                        exposicao.getId(),
                        "Exposição cadastrada com sucesso!"
                )
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar Exposição por ID",
            description = "Método que atualiza os dados de uma exposição existente."
    )
    public ResponseEntity<ExposicaoResponse> atualizarExposicao(
            @PathVariable Long id,
            @RequestBody ExposicaoRequest request) {

        Exposicao exposicao = exposicaoRepository.findById(id).orElse(null);

        if (exposicao == null) {
            return ResponseEntity.notFound().build();
        }

        exposicao.setNome(request.getNome());
        exposicao.setLocal(request.getLocal());
        exposicao.setDataInicio(request.getDataInicio());
        exposicao.setDataFim(request.getDataFim());

        exposicaoRepository.save(exposicao);

        return ResponseEntity.ok(
                new ExposicaoResponse(
                        exposicao.getId(),
                        "Exposição atualizada com sucesso!"
                )
        );
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir Exposição",
            description = "Método responsável por excluir uma exposição pelo ID."
    )
    public ResponseEntity<ExposicaoResponse> excluirExposicao(
            @PathVariable Long id) {

        Exposicao exposicao =
                exposicaoRepository.findById(id)
                        .orElse(null);

        if (exposicao == null) {

            return ResponseEntity.notFound().build();
        }

        exposicaoRepository.delete(exposicao);

        return ResponseEntity.ok(
                new ExposicaoResponse(
                        id,
                        "Exposição excluída com sucesso!"
                )
        );
    }
}
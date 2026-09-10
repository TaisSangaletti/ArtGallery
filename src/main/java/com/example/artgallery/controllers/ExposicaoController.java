package com.example.artgallery.controllers;

import com.example.artgallery.DTO.ExposicaoRequest;
import com.example.artgallery.DTO.ExposicaoResponse;
import com.example.artgallery.entities.Exposicao;
import com.example.artgallery.repository.ExposicaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exposicoes")
public class ExposicaoController {

    private final ExposicaoRepository exposicaoRepository;

    public ExposicaoController(ExposicaoRepository exposicaoRepository) {
        this.exposicaoRepository = exposicaoRepository;
    }

    @GetMapping
    public List<Exposicao> getExposicoes() {
        return exposicaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Exposicao> exposicaoPorId(@PathVariable Long id) {
        return exposicaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ExposicaoResponse> cadastrarExposicao(
            @RequestBody ExposicaoRequest request) {

        Exposicao exposicao = new Exposicao();
        exposicao.setNome(request.getNome());
        exposicao.setLocal(request.getLocal());
        exposicao.setDataInicio(request.getDataInicio());

        exposicao = exposicaoRepository.save(exposicao);

        return ResponseEntity.ok(
                new ExposicaoResponse(exposicao.getId(), "Exposição cadastrada com sucesso!")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExposicaoResponse> atualizarExposicao(
            @PathVariable Long id,
            @RequestBody ExposicaoRequest request) {

        Exposicao exposicao = exposicaoRepository.findById(id).orElse(null);

        if (exposicao != null) {
            exposicao.setNome(request.getNome());
            exposicao.setLocal(request.getLocal());
            exposicao.setDataInicio(request.getDataInicio());

            exposicaoRepository.save(exposicao);

            return ResponseEntity.ok(
                    new ExposicaoResponse(exposicao.getId(), "Exposição atualizada com sucesso!")
            );
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarExposicao(@PathVariable Long id) {

        Exposicao exposicao = exposicaoRepository.findById(id).orElse(null);

        if (exposicao != null) {
            exposicaoRepository.delete(exposicao);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
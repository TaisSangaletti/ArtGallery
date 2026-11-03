package com.example.artgallery.controllers;

import com.example.artgallery.DTO.ExposicaoRequest;
import com.example.artgallery.DTO.ExposicaoResponse;
import com.example.artgallery.entities.Exposicao;
import com.example.artgallery.repository.ExposicaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exposicoes")
@Tag(name = "Exposição Controller", description = "Classe responsável por fazer o controle do CRUD de Exposições!")

public class ExposicaoController {

    private final ExposicaoRepository exposicaoRepository;

    public ExposicaoController(ExposicaoRepository exposicaoRepository) {
        this.exposicaoRepository = exposicaoRepository;
    }

    @GetMapping
    @Operation(summary = "Listar Todas as Exposições", description = "Método que consulta e retorna uma lista com todas as exposições cadastradas no banco.")
    public List<Exposicao> getExposicoes() {
        return exposicaoRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Exposição por ID", description = "Método que consulta e retorna uma exposição localizada pelo ID na URL, ou retorna o status de não encontrado caso o registro não exista.")
    public ResponseEntity<Exposicao> exposicaoPorId(@PathVariable Long id) {
        return exposicaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Cadastrar Nova Exposição", description = "Método que realiza o cadastro de uma nova exposição, preenchendo seus dados com base no DTO recebido e salvando o novo registro no banco.")
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
    @Operation(summary = "Atualizar Exposição por ID", description = "Método que atualiza os dados de uma exposição existente localizada pelo ID na URL, recebendo as novas informações via DTO e salvando as alterações no banco.")
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
    @Operation(summary = "Deletar Exposição por ID", description = "Método que deleta permanentemente uma exposição localizada pelo ID na URL, removendo o registro do banco de dados.")
    public ResponseEntity<Void> deletarExposicao(@PathVariable Long id) {

        Exposicao exposicao = exposicaoRepository.findById(id).orElse(null);

        if (exposicao != null) {
            exposicaoRepository.delete(exposicao);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
package com.example.artgallery.controllers;

import com.example.artgallery.DTO.ExposicaoRequest;
import com.example.artgallery.DTO.ExposicaoResponse;
import com.example.artgallery.entities.Exposicao;
import com.example.artgallery.entities.ObraDeArte;
import com.example.artgallery.repository.ExposicaoRepository;
import com.example.artgallery.repository.ObraDeArteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
            ObraDeArteRepository obraDeArteRepository
    ) {
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
            description = "Método que consulta e retorna uma exposição localizada pelo ID na URL."
    )
    public ResponseEntity<Exposicao> exposicaoPorId(@PathVariable Long id) {
        return exposicaoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar Nova Exposição",
            description = "Método que realiza o cadastro de uma nova exposição validando conflito de datas."
    )
    public ResponseEntity<?> cadastrarExposicao(@RequestBody ExposicaoRequest request) {
        try {
            List<ObraDeArte> obras = buscarEValidarObras(
                    request.getObraIds(),
                    request.getDataInicio(),
                    request.getDataFim(),
                    null
            );

            Exposicao exposicao = new Exposicao();
            exposicao.setNome(request.getNome());
            exposicao.setLocal(request.getLocal());
            exposicao.setDataInicio(request.getDataInicio());
            exposicao.setDataFim(request.getDataFim());
            exposicao.setObras(obras);

            exposicao = exposicaoRepository.save(exposicao);

            return ResponseEntity.ok(
                    new ExposicaoResponse(
                            exposicao.getId(),
                            "Exposição cadastrada com sucesso!"
                    )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar Exposição por ID",
            description = "Método que atualiza os dados de uma exposição existente validando conflito de datas."
    )
    public ResponseEntity<?> atualizarExposicao(
            @PathVariable Long id,
            @RequestBody ExposicaoRequest request) {

        Exposicao exposicao = exposicaoRepository.findById(id).orElse(null);
        if (exposicao == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            List<ObraDeArte> obras = buscarEValidarObras(
                    request.getObraIds(),
                    request.getDataInicio(),
                    request.getDataFim(),
                    id
            );

            exposicao.setNome(request.getNome());
            exposicao.setLocal(request.getLocal());
            exposicao.setDataInicio(request.getDataInicio());
            exposicao.setDataFim(request.getDataFim());
            exposicao.setObras(obras);

            exposicaoRepository.save(exposicao);

            return ResponseEntity.ok(
                    new ExposicaoResponse(
                            exposicao.getId(),
                            "Exposição atualizada com sucesso!"
                    )
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar Exposição por ID",
            description = "Método que remove uma exposição do banco."
    )
    public ResponseEntity<Void> deletarExposicao(@PathVariable Long id) {
        Exposicao exposicao = exposicaoRepository.findById(id).orElse(null);
        if (exposicao == null) {
            return ResponseEntity.notFound().build();
        }

        exposicaoRepository.delete(exposicao);
        return ResponseEntity.ok().build();
    }

    private List<ObraDeArte> buscarEValidarObras(
            List<Long> obraIds,
            LocalDate dataInicio,
            LocalDate dataFim,
            Long exposicaoIdAtual
    ) {
        if (dataInicio == null || dataFim == null || dataFim.isBefore(dataInicio)) {
            throw new IllegalArgumentException(
                    "Datas inválidas: dataFim deve ser igual ou posterior a dataInicio."
            );
        }

        if (obraIds == null || obraIds.isEmpty()) {
            return new ArrayList<>();
        }

        List<ObraDeArte> obras = obraDeArteRepository.findAllById(obraIds);
        if (obras.size() != obraIds.size()) {
            throw new IllegalArgumentException("Uma ou mais obras informadas não foram encontradas.");
        }

        for (ObraDeArte obra : obras) {
            boolean temConflito = exposicaoRepository.existsConflitoDeDataParaObra(
                    obra.getId(),
                    dataInicio,
                    dataFim,
                    exposicaoIdAtual
            );
            if (temConflito) {
                throw new IllegalStateException(
                        "A obra '" + obra.getTitulo() + "' já está vinculada a outra exposição no período informado."
                );
            }
        }

        return obras;
    }
}
package com.example.artgallery.controllers;

import com.example.artgallery.DTO.ObraDeArteRequest;
import com.example.artgallery.DTO.ObraDeArteResponse;
import com.example.artgallery.entities.ObraDeArte;
import com.example.artgallery.repository.ObraDeArteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
@Tag(
        name = "Obra de arte Controller",
        description = "Classe responsável por fazer o controle do CRUD de Obras de arte!"
)
public class ObraDeArteController {

    private final ObraDeArteRepository obraDeArteRepository;

    public ObraDeArteController(ObraDeArteRepository obraDeArteRepository) {
        this.obraDeArteRepository = obraDeArteRepository;
    }

    @GetMapping
    @Operation(
            summary = "Listar Todas as obras de arte",
            description = "Método que consulta e retorna uma lista com todas as obras de arte cadastradas no banco."
    )
    public List<ObraDeArte> getObras() {
        return obraDeArteRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar obra de arte por ID",
            description = "Método que consulta e retorna uma obra de arte pelo ID."
    )
    public ResponseEntity<ObraDeArte> obraPorId(@PathVariable Long id) {

        return obraDeArteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar Nova Obra de Arte",
            description = "Método que realiza o cadastro de uma nova obra de arte."
    )
    public ResponseEntity<ObraDeArteResponse> cadastrarObra(
            @RequestBody ObraDeArteRequest request) {

        ObraDeArte obra = new ObraDeArte();

        obra.setTitulo(request.getTitulo());
        obra.setTecnica(request.getTecnica());
        obra.setAnoCriacao(request.getAnoCriacao());
        obra.setValorSeguro(request.getValorSeguro());

        obra = obraDeArteRepository.save(obra);

        return ResponseEntity.ok(
                new ObraDeArteResponse(
                        obra.getId(),
                        "Obra cadastrada com sucesso!"
                )
        );
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar Obra de Arte por ID",
            description = "Método que atualiza uma obra de arte existente."
    )
    public ResponseEntity<ObraDeArteResponse> atualizarObra(
            @PathVariable Long id,
            @RequestBody ObraDeArteRequest request) {

        ObraDeArte obra = obraDeArteRepository.findById(id).orElse(null);

        if (obra == null) {
            return ResponseEntity.notFound().build();
        }

        obra.setTitulo(request.getTitulo());
        obra.setTecnica(request.getTecnica());
        obra.setAnoCriacao(request.getAnoCriacao());
        obra.setValorSeguro(request.getValorSeguro());

        obraDeArteRepository.save(obra);

        return ResponseEntity.ok(
                new ObraDeArteResponse(
                        obra.getId(),
                        "Obra atualizada com sucesso!"
                )
        );
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar Obra de Arte por ID",
            description = "Método que remove uma obra de arte do banco."
    )
    public ResponseEntity<Void> deletarObra(@PathVariable Long id) {

        ObraDeArte obra = obraDeArteRepository.findById(id).orElse(null);

        if (obra == null) {
            return ResponseEntity.notFound().build();
        }

        obraDeArteRepository.delete(obra);

        return ResponseEntity.ok().build();
    }
}
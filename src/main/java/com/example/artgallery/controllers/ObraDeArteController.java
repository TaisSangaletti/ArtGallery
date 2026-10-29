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
@Tag(name = "Obra de arte Controller", description = "Classe responsável por fazer o controle do CRUD de Obras de arte!")

public class ObraDeArteController {

    private final ObraDeArteRepository obraDeArteRepository;

    public ObraDeArteController(ObraDeArteRepository obraDeArteRepository) {
        this.obraDeArteRepository = obraDeArteRepository;
    }

    @GetMapping
    @Operation(summary = "Listar Todas as obras de arte", description = "Método que consulta e retorna uma lista com todas as obras de arte cadastradas no banco, convertendo cada registro para o formato de DTO.")

    public List<ObraDeArte> getObras() {
        return obraDeArteRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listar Todas as obras de arte por ID", description = "Método que consulta e retorna uma lista com todas as obras de arte, por ID, cadastradas no banco, que retorna somente um usário e recebe como parâmetro o Path Variable ID e retorna como saída o objeto do DTO")
    public ResponseEntity<ObraDeArte> obraPorId(@PathVariable Long id) {
        return obraDeArteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ObraDeArteResponse> cadastrarObra(
            @RequestBody ObraDeArteRequest request) {

        ObraDeArte obra = new ObraDeArte();
        obra.setTitulo(request.getTitulo());
        obra.setTecnica(request.getTecnica());
        obra.setAnoCriacao(request.getAnoCriacao());

        obra = obraDeArteRepository.save(obra);

        return ResponseEntity.ok(
                new ObraDeArteResponse(obra.getId(), "Obra cadastrada com sucesso!")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraDeArteResponse> atualizarObra(
            @PathVariable Long id,
            @RequestBody ObraDeArteRequest request) {

        ObraDeArte obra = obraDeArteRepository.findById(id).orElse(null);

        if (obra != null) {
            obra.setTitulo(request.getTitulo());
            obra.setTecnica(request.getTecnica());
            obra.setAnoCriacao(request.getAnoCriacao());

            obraDeArteRepository.save(obra);

            return ResponseEntity.ok(
                    new ObraDeArteResponse(obra.getId(), "Obra atualizada com sucesso!")
            );
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarObra(@PathVariable Long id) {

        ObraDeArte obra = obraDeArteRepository.findById(id).orElse(null);

        if (obra != null) {
            obraDeArteRepository.delete(obra);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
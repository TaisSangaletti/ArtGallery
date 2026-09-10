package com.example.artgallery.controllers;

import com.example.artgallery.DTO.ObraDeArteRequest;
import com.example.artgallery.DTO.ObraDeArteResponse;
import com.example.artgallery.entities.ObraDeArte;
import com.example.artgallery.repository.ObraDeArteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obras")
public class ObraDeArteController {

    private final ObraDeArteRepository obraDeArteRepository;

    public ObraDeArteController(ObraDeArteRepository obraDeArteRepository) {
        this.obraDeArteRepository = obraDeArteRepository;
    }

    @GetMapping
    public List<ObraDeArte> getObras() {
        return obraDeArteRepository.findAll();
    }

    @GetMapping("/{id}")
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
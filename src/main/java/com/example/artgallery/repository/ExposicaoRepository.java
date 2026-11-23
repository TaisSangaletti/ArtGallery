package com.example.artgallery.repository;

import com.example.artgallery.entities.Exposicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ExposicaoRepository extends JpaRepository<Exposicao, Long> {

    @Query("""
            SELECT COUNT(e) > 0
            FROM Exposicao e
            JOIN e.obras o
            WHERE o.id = :obraId
              AND (:exposicaoId IS NULL OR e.id <> :exposicaoId)
              AND :dataInicio <= e.dataFim
              AND :dataFim >= e.dataInicio
            """)
    boolean existsConflitoDeDataParaObra(
            @Param("obraId") Long obraId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            @Param("exposicaoId") Long exposicaoId
    );
}
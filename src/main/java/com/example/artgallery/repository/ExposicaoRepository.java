package com.example.artgallery.repository;

import com.example.artgallery.entities.Exposicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExposicaoRepository extends JpaRepository<Exposicao, Long> {

    @Query(
            "SELECT COUNT(e) " +
                    "FROM Exposicao e " +
                    "JOIN e.obras o " +
                    "WHERE o.id = :obraId " +
                    "AND e.dataInicio = :dataInicio"
    )
    Long existeObraNaMesmaData(
            @Param("obraId") Long obraId,
            @Param("dataInicio") String dataInicio
    );
}
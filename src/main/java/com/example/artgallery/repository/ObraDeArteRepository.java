package com.example.artgallery.repository;

import com.example.artgallery.entities.ObraDeArte;
import com.example.artgallery.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraDeArteRepository extends JpaRepository<ObraDeArte,Long> {
}

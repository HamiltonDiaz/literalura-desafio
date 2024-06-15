package com.aluracursos.literalura_desafio.repository;

import com.aluracursos.literalura_desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LibroRepository extends JpaRepository <Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.idioma ILIKE %:idioma%")
    List<Libro> busarLibroPorIdioma(String idioma);
}

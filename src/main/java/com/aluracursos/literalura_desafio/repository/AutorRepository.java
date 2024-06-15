package com.aluracursos.literalura_desafio.repository;

import com.aluracursos.literalura_desafio.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository <Autor, Long> {
    Autor findByNombreContainsIgnoreCase(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeFallecimiento >= :fecha AND a.fechaDeNacimiento <= :fecha")
    List<Autor> buscarAutorVivo(int fecha);


}

package com.aluracursos.literalura_desafio.record;

import com.aluracursos.literalura_desafio.model.Autor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro (
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List <DatosAutor> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Double numeroDeDescargas
) {
}

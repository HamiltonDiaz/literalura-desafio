package com.aluracursos.literalura_desafio.model;

import com.aluracursos.literalura_desafio.record.DatosLibro;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma;
    private Double numeroDeDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma= datosLibro.idioma().get(0);
        this.numeroDeDescargas = datosLibro.numeroDeDescargas() != null ? datosLibro.numeroDeDescargas() : 0.0;
        this.autor= new Autor(datosLibro.autor().get(0));
    }

    @Override
    public String toString() {
        return  "-----LIBRO-----" + "\n" +
                "Titulo: " + titulo + "\n" +
                autor +
                "Idioma: " + idioma + "\n" +
                "Numero de descargas:" + numeroDeDescargas + "\n" +
                "--------------";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

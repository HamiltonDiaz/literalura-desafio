package com.aluracursos.literalura_desafio.model;

import com.aluracursos.literalura_desafio.record.DatosAutor;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeFallecimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Libro> libros;

    //Constructores
    public Autor() {}
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeFallecimiento= datosAutor.fechaDeFallecimiento();
        this.fechaDeNacimiento= datosAutor.fechaDeNacimiento();
    }

    @Override
    public String toString() {
        String nacimiento= fechaDeNacimiento==null ? " Sin fecha"  : String.valueOf(fechaDeNacimiento);
        String fallecimiento= fechaDeFallecimiento==null ? " Sin fecha"  :  String.valueOf(fechaDeFallecimiento);

        return "Autor:" + nombre + "\n" +
                "Fecha de nacimiento: " + nacimiento + "\n" +
                "Fecha de fallecimiento:" + fallecimiento + "\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeFallecimiento() {
        return fechaDeFallecimiento;
    }

    public void setFechaDeFallecimiento(Integer fechaDeFallecimiento) {
        this.fechaDeFallecimiento = fechaDeFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}

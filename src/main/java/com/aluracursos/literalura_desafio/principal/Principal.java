package com.aluracursos.literalura_desafio.principal;

import com.aluracursos.literalura_desafio.model.Autor;
import com.aluracursos.literalura_desafio.model.Libro;
import com.aluracursos.literalura_desafio.record.DatosApi;
import com.aluracursos.literalura_desafio.record.DatosLibro;
import com.aluracursos.literalura_desafio.repository.AutorRepository;
import com.aluracursos.literalura_desafio.repository.LibroRepository;
import com.aluracursos.literalura_desafio.services.ConsumoAPI;
import com.aluracursos.literalura_desafio.services.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado= new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE="https://gutendex.com/books/";
    ConvierteDatos conversor= new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal() {
    }

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository= libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu(){
        var opcion = -1;
        var menu= """
                1 - Buscar libro por título.
                2 - Listar libros registrados.
                3 - Listar autores registrados.
                4 - Listar autores vivos en un determinado año.
                5 - Listar Libros por Idioma.
                0 - Salir.
                """;

        while (opcion!=0){
            System.out.println(menu);
            opcion= teclado.nextInt();
            teclado.nextLine();
            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    mostrarLibros();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    mostrarAutoresVivos();
                    break;
                case 5:
                    buscarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
            }
        }
    }

    //Opción 1 Método para buscar libro y guardarlo en la bbdd
    private void buscarLibro() {
        System.out.println("Ingresa el nombre del libro que deseas buscar:");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+").toLowerCase());
        //System.out.println(json);
        DatosApi datosApi= conversor.obtenerDatos(json, DatosApi.class);

        Optional<Libro> libroEncontrado= datosApi.libros().stream()
                .map(Libro::new)
                .findFirst();

        if (libroEncontrado.isPresent()){
            //System.out.println(libroEncontrado.get());
            //Consulta autor por nombre
            /*Autor autor = autorRepository.findByNombreContainsIgnoreCase(libroEncontrado.get().getAutor().getNombre());
            if (autor == null) {
                // Si el retorno de la bbbd es nulll se procede a crear el autor
                Autor nuevoAutor = libroEncontrado.get().getAutor();

                //Retorna el id del autor, se usa para pasar el autor al libro con el id.
                autor = autorRepository.save(nuevoAutor);
                //System.out.println(autor);
            }

            //Esta instancia no tiene datos de autor
            Libro libro = libroEncontrado.get();*/

            try {
                Libro libro = libroEncontrado.get();
                libroRepository.save(libro);
                System.out.println("Libro guardado");
                System.out.println(libro);
            }catch (DataIntegrityViolationException e){
                System.out.println(e);
                System.out.println("Libro ya registrado");
            }

        }else {
            System.out.println("Libro no encontrado");
        }
    }

    //Opción 2 Método para listar todos los libros
    public void mostrarLibros(){
        List<Libro> libros = libroRepository.findAll();
        libros.forEach(System.out::println);
        //System.out.println(libros);
        if (libros.isEmpty()){
            System.out.println("Aún no hay libros registrados.");
        }else{

            libros.forEach(System.out::println);
        }
    }
    //Opción 3 Método para listar todos los autores
    public void mostrarAutores(){
        List<Autor> autores = autorRepository.findAll();
//        List<Autor> autores = autorRepository.listarAutores();
        if (autores.isEmpty()){
            System.out.println("Aún no hay autores registrados.");
        }else autores.forEach(System.out::println);
    }

    //Opción 4 Método para listar autores vivos
    public void mostrarAutoresVivos(){
        System.out.println("Ingrese el año dónde el autor(es) estaban con vida");
        var fecha=teclado.nextInt();
        List<Autor> autores = autorRepository.buscarAutorVivo(fecha);
        if (autores.isEmpty()){
            System.out.println("No hay autores vivos registrados en el año " + fecha);
        }else autores.forEach(System.out::println);
    }

    //Opción 5 Método para buscar libros por idioma
    public void buscarLibroPorIdioma(){
        var submenu= """
                Ingrese el idioma para buscar los libros:
                es - Español
                en - Inglés
                fr - Francés
                pt - Portugués
                """;
        System.out.println(submenu);
        var idioma= teclado.nextLine();
        List<Libro> libros = libroRepository.busarLibroPorIdioma(idioma);
        if (libros.isEmpty()){
            System.out.println("No hay libros  registrados con el idioma seleccionado" );
        }else libros.forEach(System.out::println);
    }

}

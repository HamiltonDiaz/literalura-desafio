package com.aluracursos.literalura_desafio;

import com.aluracursos.literalura_desafio.principal.Principal;
import com.aluracursos.literalura_desafio.repository.AutorRepository;
import com.aluracursos.literalura_desafio.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraDesafioApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository libroRepository;
	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraDesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal= new Principal(libroRepository, autorRepository);
		//Principal principal= new Principal();
		principal.muestraElMenu();
	}
}

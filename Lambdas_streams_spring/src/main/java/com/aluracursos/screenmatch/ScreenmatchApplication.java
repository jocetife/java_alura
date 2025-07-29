package com.aluracursos.screenmatch;


//import com.aluracursos.screenmatch.principal.EjemploStreams;
//import com.aluracursos.screenmatch.principal.Principal;
import com.aluracursos.screenmatch.desafio_libros.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class ScreenmatchApplication  implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraelMenu();

		//EjemploStreams ejemploStreams = new EjemploStreams();
		//ejemploStreams.muestraEjemplo();

	}
}

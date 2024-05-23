package com.backend.clinica_odontologica;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.clinica_odontologica.repository.dbConnection.H2Connection;

@SpringBootApplication
@RestController

public class ClinicaOdontologicaApplication {
	private static Logger LOGGER = LoggerFactory.getLogger(ClinicaOdontologicaApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		H2Connection.crearTablas();
		LOGGER.info("clinicaOdontologica is now running...");
	}
	@GetMapping("/hola")
	public String saludar(){
		return "clinica odontologica";
	}

}

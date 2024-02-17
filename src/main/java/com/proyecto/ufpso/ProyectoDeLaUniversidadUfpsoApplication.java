package com.proyecto.ufpso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProyectoDeLaUniversidadUfpsoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoDeLaUniversidadUfpsoApplication.class, args);
	}

}

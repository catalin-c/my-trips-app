package ro.siit.mytripsapp;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MyTripsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyTripsAppApplication.class, args);
	}


	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
}

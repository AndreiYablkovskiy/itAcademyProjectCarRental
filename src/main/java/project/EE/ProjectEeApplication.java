package project.EE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import project.EE.service.impl.CarServiceImpl;

@SpringBootApplication
public class ProjectEeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEeApplication.class, args);

	}

}

package calculatorapp;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CalculatorappApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorappApplication.class, args);
	}

	@Bean
	public OpenAPI defineOpenAPI() {
		return new OpenAPI().info(new Info().version("1.0").title("CALCULATOR").description("Calculator application for Főnix IT"));
	}
}

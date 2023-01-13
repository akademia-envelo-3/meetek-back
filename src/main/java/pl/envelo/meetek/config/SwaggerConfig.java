package pl.envelo.meetek.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Meetek API", version = "1.0", description = "Meetek swagger init"))
public class SwaggerConfig {

}

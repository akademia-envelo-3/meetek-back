package pl.envelo.meetek.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Meetek API", version = "${app.version}", description = "Meetek API documentation"))
public class SwaggerConfig {

}

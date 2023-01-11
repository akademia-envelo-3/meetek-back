package pl.envelo.meetek.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Meetek API", version = "1.0", description = "Meetek swagger init"))
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

//    private ApiInfo metaData() {
//        return new ApiInfoBuilder()
//                .title("Meetek")
//                .description("Event planner application")
//                .version("1.0.0")
//                .license("Apache 2.0")
//                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
//                .contact(new Contact("Akademia Envelo", "none", "none"))
//                .build();
//    }
}

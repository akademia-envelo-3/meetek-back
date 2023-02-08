package pl.envelo.meetek.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Meetek API", version = "${app.version}", description = "Meetek API documentation"))
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi allOpenApi() {
        String[] packages = {"pl.envelo.meetek"};
        return GroupedOpenApi.builder().group("all").packagesToScan(packages).build();
    }

    @Bean
    public GroupedOpenApi eventsOpenApi() {
        String[] packages = {"pl.envelo.meetek.domain.event"};
        return GroupedOpenApi.builder().group("events").packagesToScan(packages).build();
    }

    @Bean
    public GroupedOpenApi sectionsOpenApi() {
        String[] packages = {"pl.envelo.meetek.domain.group"};
        return GroupedOpenApi.builder().group("sections").packagesToScan(packages).build();
    }

    @Bean
    public GroupedOpenApi notificationsOpenApi() {
        String[] packages = {"pl.envelo.meetek.domain.notification"};
        return GroupedOpenApi.builder().group("notifications").packagesToScan(packages).build();
    }

    @Bean
    public GroupedOpenApi requestOpenApi() {
        String[] packages = {"pl.envelo.meetek.domain.request"};
        return GroupedOpenApi.builder().group("requests").packagesToScan(packages).build();
    }

    @Bean
    public GroupedOpenApi surveyOpenApi() {
        String[] packages = {"pl.envelo.meetek.domain.survey"};
        return GroupedOpenApi.builder().group("surveys").packagesToScan(packages).build();
    }

    @Bean
    public GroupedOpenApi userOpenApi() {
        String[] packages = {"pl.envelo.meetek.domain.user"};
        return GroupedOpenApi.builder().group("users").packagesToScan(packages).build();
    }

    @Bean
    public GroupedOpenApi categoryOpenApi() {
        String[] packages = {"pl.envelo.meetek.domain.category"};
        return GroupedOpenApi.builder().group("categories").packagesToScan(packages).build();
    }

    @Bean
    public GroupedOpenApi hashtagOpenApi() {
        String[] packages = {"pl.envelo.meetek.domain.hashtag"};
        return GroupedOpenApi.builder().group("hashtags").packagesToScan(packages).build();
    }

}

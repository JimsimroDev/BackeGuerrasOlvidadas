package com.jimsimrodev.guerrasOlvidadas.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI custonOpenApi() {

    return new OpenAPI()
        .info(new Info()
            .title("Guerras Olividadas API -------")
            .version("0.0.1")
            .description("API para un juego de hostria y guerras epicas")
            .termsOfService("http://swagger.io/terms/")
            .license(new License()
                .name("Apache 2.0").url("http://springdoc.org")));

  }
}

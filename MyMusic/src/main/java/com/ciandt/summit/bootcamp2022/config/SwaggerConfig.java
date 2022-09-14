package com.ciandt.summit.bootcamp2022.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("MyMusic API")
                        .description(
                                "A new Spotify-like API. You are able to search " +
                                        "for musics and artists as well as create new playlists")
                        .version("1.0.0")
                );
    }

}

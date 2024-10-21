package com.sahin.todoapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Configuration
@OpenAPIDefinition

public class SwaggerConfig {

    @Bean
    public OpenAPI basedOpenAPI (){

        ApiResponse badRequest = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 400, \"status\" : \"Bad Request\", \"Message\" : \"Bad Request\"}"))));
        ApiResponse internalServerError = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\"code\" : 500, \"status\" : \"internalServerError\", \"Message\" : \"internalServerError\"}"))));
        ApiResponse successfulResponse = new ApiResponse().content(
                new Content().addMediaType("application/json",
                        new io.swagger.v3.oas.models.media.MediaType().addExamples("default",
                                new Example().value("{\n" +
                                        "  \"id\": 0,\n" +
                                        "  \"task_name\": \"string\",\n" +
                                        "  \"status\": true\n" +
                                        "}"))));


        Components components = new Components();
        components.addResponses("badRequest",badRequest);g
        components.addResponses("internalServerError",internalServerError);
        components.addResponses("successfulResponse",successfulResponse);


        return new OpenAPI().components(components).info(new Info().title("To Do Application")
                .version("1.0.0")
                .description("Basic To Do Application"));

    }

}
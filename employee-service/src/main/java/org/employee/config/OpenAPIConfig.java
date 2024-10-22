package org.employee.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.ObjectSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.TimeZone;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer")
public class OpenAPIConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        ObjectSchema myCustomSchema = new ObjectSchema();
        myCustomSchema.addProperty("username", new Schema<>().type("string")._default("swagger"));
        myCustomSchema.addProperty("email", new Schema<>().type("string")._default("dummy@email"));

        var x = new OpenAPI()
                .info(new Info().title("SpringBoot API")
                        .description("SpringBoot sample application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("SpringBoot Wiki Documentation")
                        .url("https://springboot.wiki.github.org/docs"));

//        x.getComponents().addParameters("ana", new HeaderParameter()
//                .name("test-header")
//                .required(true)
//                .schema(myCustomSchema).explode(true)
//                .style(Parameter.StyleEnum.SIMPLE));
        return x;
    }

    @Bean
    public GlobalOpenApiCustomizer customizer() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> operation.addParametersItem(new HeaderParameter().name("Time-Zone")
                        .description("Time Zone")
                        .schema(new Schema()._enum(Arrays.stream(TimeZone.getAvailableIDs()).
                                toList()))
                        .required(true)));
    }
}

package com.jcbj.hemisferiod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ApiGestionVentasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGestionVentasApplication.class, args);
    }
    
        private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("API Gestion de Ventas")
                .description("Esta es una API-REST que permite gestionar "
                        + "los clientes, el inventario y las ventas de un negocio, mediante las operaciones basicas de CRUD.")
                .contact(new springfox.documentation.service.Contact("Juan Bermúdez","","correo@mail.com"))
                .license("JCBJ License")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("JCBJ-api-gestion-ventas")
                .apiInfo(apiInfo())
                .select().
                apis(RequestHandlerSelectors.basePackage("com.jcbj.hemisferiod"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag("API Gestion Clientes", "Todos los metodos relacionados con la gestión de Clientes"))
                .tags(new Tag("API Gestion Facturas", "Todos los metodos relacionados con la gestión de Facturas"))
                .tags(new Tag("API Gestion Productos", "Todos los metodos relacionados con la gestión de Productos"));
    }

}

package com.example.washer;


import org.modelmapper.internal.util.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Collections;

@SpringBootApplication
public class WasherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WasherApplication.class, args);
    }
//
//    @Bean
//    public Docket newsApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//
//    }
//
//    @Bean
//    SecurityConfiguration security() {
//        return new SecurityConfiguration(
//                "test-app-client-id",
//                "test-app-client-secret",
//                "test-app-realm",
//                "test-app",
//                "",
//                ApiKeyVehicle.HEADER,
//                "Authorization",
//                "," /*scope separator*/);
//    }
//
//
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Spring REST Sample with Swagger")
//                .description("Spring REST Sample with Swagger")
//                .version("2.0")
//                .build();
//    }

}

package ru.comfortsoft.numbersearchservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Сервис по поиску числа",
                description = "Описание спецификации API сервиса по поиску числа.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Suslov Kirill",
                        url = "https://t.me/euchekavelo"
                )
        )
)
@Configuration
public class OpenApiConfig {
}

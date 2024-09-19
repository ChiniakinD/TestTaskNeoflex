package com.chiniakin.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Микросервис \"Калькулятор отпускных\"",
                contact = @Contact(
                        name = "ChiniakinD",
                        url = "https://github.com/ChiniakinD/TestTaskNeoflex"
                )
        )
)
public class OpenApiConfig {
}

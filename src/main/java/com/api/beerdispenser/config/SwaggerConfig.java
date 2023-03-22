package com.api.beerdispenser.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Beer Tap Dispenser API by KevinSDJ",
                description ="""
                        A challenger from rviewer , to practice my skills with java , spring and sql
                        """,
                contact = @Contact(name = "Kevin De jesus", url = "https://reflectoring.io", email = "sebadjkevin@gmail.com"),
                license = @License(name = "MIT Licence", url = "https://github.com/thombergs/code-examples/blob/master/LICENSE")),
        servers = @Server(url = "http://localhost:8080")
)
public class SwaggerConfig {
    
}

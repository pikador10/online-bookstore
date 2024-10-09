package com.hw.bookstore.property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "auth.token")
public record AuthProperty(

        @NotBlank
        String secret,

        @NotNull
        Duration expiration
) {
}

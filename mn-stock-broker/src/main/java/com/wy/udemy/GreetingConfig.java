package com.wy.udemy;

import io.micronaut.context.annotation.ConfigurationInject;
import io.micronaut.context.annotation.ConfigurationProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@ConfigurationProperties("hello.config.greeting")
public class GreetingConfig {

    private final String de;
    private final String en;

    public String getDe() {
        return de;
    }

    public String getEn() {
        return en;
    }

    @ConfigurationInject
    public GreetingConfig(@NotBlank final String de, @NotBlank final String en) {
        this.de = de;
        this.en = en;
    }
}

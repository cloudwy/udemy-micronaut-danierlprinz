package com.wy.udemy;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@ConfigurationProperties("hello.world.translation")
public interface HelloWorldTranslationConfig {
    String getDe();
    String getEn();
}

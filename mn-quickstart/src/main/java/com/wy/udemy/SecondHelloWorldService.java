package com.wy.udemy;

import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

@Primary
@Singleton
public class SecondHelloWorldService implements MyService {

    @Override
    public String helloFromService(){
        return "Hello from Second Service";
    }
}

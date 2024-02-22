package com.wy.udemy;

import jakarta.inject.Singleton;

@Singleton
public class HelloWorldService {
    public String helloFromService(){
        return "Hello from Service";
    }
}

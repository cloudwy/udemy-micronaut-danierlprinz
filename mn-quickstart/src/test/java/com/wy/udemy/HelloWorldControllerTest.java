package com.wy.udemy;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import jakarta.inject.Inject;
import io.micronaut.http.client.HttpClient;
import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class HelloWorldControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void helloWorldEndpointResponseWithProperContent(){
        var response = client.toBlocking().retrieve("/hello");
        assertEquals("Hello from Second Service", response);
    }

    @Test
    void helloWorldEndpointRespondsWithProperStatusCodeAndContent(){
        var response = client.toBlocking().exchange("/hello", String.class);
        assertEquals("Hello from Second Service", response.getBody().get());
        assertEquals(HttpStatus.OK, response.getStatus());
    }

    @Test
    void helloFromConfigEndpointReturnsMessageFromConfigFile(){
        var response = client.toBlocking().exchange("/hello/config", String.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("Hello from application.yml", response.getBody().get());
    }

    @Test
    void helloFromTranslationEndpointReturnsContentFromConfigFile() {
        var response = client.toBlocking().exchange("/hello/translation", JsonNode.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals("-", response.getBody().get().toString());
    }

}

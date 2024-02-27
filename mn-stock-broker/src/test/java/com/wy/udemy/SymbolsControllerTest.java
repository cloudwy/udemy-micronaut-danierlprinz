package com.wy.udemy;

import com.wy.udemy.broker.InMemoryStore;
import com.wy.udemy.broker.Symbol;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class SymbolsControllerTest {
    @Inject
    @Client("/symbols")
    HttpClient client;

    @Inject
    InMemoryStore inMemoryStore;

    @BeforeEach
    void setup(){
        inMemoryStore.initializeWith(10);
    }

    @Test
    void symbolsEndpointReturnsListofSymbol() {
        var response = client.toBlocking().exchange("/", JsonNode.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(10, response.getBody().get().size());
    }

    @Test
    void symbolsEndpointReturnsTheCorrectSymbol(){
        var testSymbol = new Symbol("TEST");
        inMemoryStore.getSymbols().put(testSymbol.value(), testSymbol);
        Symbol symbols = inMemoryStore.getSymbols().get("TEST");
        var response = client.toBlocking().exchange("/" + testSymbol.value(), JsonNode.class);
        assertEquals(HttpStatus.OK, response.getStatus());
        assertEquals(testSymbol.value(), response.getBody().get().get("value").getValue());
    }
}

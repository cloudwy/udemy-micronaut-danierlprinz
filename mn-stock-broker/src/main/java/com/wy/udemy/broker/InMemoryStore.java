package com.wy.udemy.broker;

import com.github.javafaker.Faker;
import io.micronaut.http.server.netty.MicronautHttpData;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

@Singleton
public class InMemoryStore {

    private static final Logger LOG = LoggerFactory.getLogger(IntStream.class);
    private final Map<String, Symbol> symbols = new HashMap<>();
    private final Faker faker = new Faker();

    @PostConstruct
    public void initialize(){
        initializeWith(10);
    }

    public void initializeWith(int numberOfEntries){
        symbols.clear();
        IntStream.range(0, numberOfEntries).forEach(i ->
                addNewSymbol()
        );
    }

    private void addNewSymbol(){
        var symbol = new Symbol(faker.stock().nsdqSymbol());
        symbols.put(symbol.value(), symbol);
        LOG.debug("Added Symbol {}", symbol);
    }

    public Map<String, Symbol> getSymbols() {
        return symbols;
    }
}

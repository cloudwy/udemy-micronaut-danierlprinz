package com.wy.udemy.broker;

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

    @PostConstruct
    public void initialize(){
        IntStream.range(0, 10).forEach(i ->
                addNewSymbol()
        );
    }

    private void addNewSymbol(){
        var symbol = new Symbol("");
        symbols.put(symbol.value(), symbol);
        LOG.debug("Added Symbol {}", symbol);
    }
}

package com.wy.udemy.broker;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

import java.util.ArrayList;
import java.util.List;

@Controller("/symbols")
public class SymbolsController {

    @Get
    public List<Symbol> getAll(){
        return new ArrayList<>();
    }
}

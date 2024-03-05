package com.wy.udemy.broker.watchlist;

import com.wy.udemy.broker.Symbol;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

//@Introspected
//@Serializable
////@Data
//public record WatchList(List<Symbol> symbols) {
//    public WatchList(){
//        this(new ArrayList<>());
//    }
//}
@Introspected
@Serdeable
@AllArgsConstructor
//@Data
public class WatchList {

    private List<Symbol> symbols;

    public WatchList() {
        this.symbols = new ArrayList<>();
    }

    public List<Symbol> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<Symbol> symbols) {
        this.symbols = symbols;
    }
}

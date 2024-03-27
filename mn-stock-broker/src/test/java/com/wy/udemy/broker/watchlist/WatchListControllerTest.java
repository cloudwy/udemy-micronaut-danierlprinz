//package com.wy.udemy.broker.watchlist;
//
//import com.wy.udemy.broker.Symbol;
//import com.wy.udemy.broker.data.InMemoryAccountStore;
//import io.micronaut.http.HttpRequest;
//import io.micronaut.http.HttpResponse;
//import io.micronaut.http.HttpStatus;
//import io.micronaut.http.MediaType;
//import io.micronaut.http.client.HttpClient;
//import io.micronaut.http.client.annotation.Client;
//import io.micronaut.json.tree.JsonNode;
//import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
//import jakarta.inject.Inject;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.ArrayList;
//import java.util.UUID;
//import java.util.stream.Stream;
//
//import static io.micronaut.http.HttpRequest.GET;
//import static org.junit.jupiter.api.Assertions.*;
//
//@MicronautTest
//public class WatchListControllerTest {
//    private static final Logger LOG = LoggerFactory.getLogger(WatchListControllerTest.class);
//    private static final UUID TEST_ACCOUNT_ID = WatchListController.ACCOUNT_ID;
//
//    @Inject
//    @Client("/account/watchlist")
//    HttpClient client;
//
//    @Inject
//    InMemoryAccountStore inMemoryAccountStore;
//
//    @BeforeEach
//    void setup(){inMemoryAccountStore.deleteWatchList(TEST_ACCOUNT_ID);}
//
//    @Test
//    void returnEmptyWatchListForTestAccount(){
//        final WatchList result = client.toBlocking().retrieve(GET("/"), WatchList.class);
//        assertEquals(new ArrayList<>(), result.getSymbols());
//        assertTrue(inMemoryAccountStore.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
//    }
//
//    @Test
//    void returnsWatchListForTestAccount(){
//        givenWatchListForAccountExists();
//
//        var response = client.toBlocking().exchange("/", JsonNode.class);
//        assertEquals(HttpStatus.OK, response.getStatus());
//        assertEquals("{symbols=[{value=AAPL}, {value=GDDGL}, {value=MSFT}]}",
//                response.getBody().get().getValue().toString()
//        );
//
//    }
//
//    private void givenWatchListForAccountExists(){
//        WatchList watchList = new WatchList();
//        watchList.setSymbols(Stream.of("AAPL", "GDDGL", "MSFT").
//                map(Symbol::new).toList());
//        inMemoryAccountStore.updateWatchList(TEST_ACCOUNT_ID, watchList);
//    }
//
//    @Test
//    void canUpdateWatchListForTestAccount(){
//        var symbols = Stream.of("AAPL", "GOOGL", "MSFL").map(Symbol::new).toList();
//        WatchList watchList = new WatchList();
//        watchList.setSymbols(symbols);
//        final var request = HttpRequest.PUT("/", watchList).accept(MediaType.APPLICATION_JSON);
//        final HttpResponse<Object> added = client.toBlocking().exchange(request);
//        assertEquals(HttpStatus.OK, added.getStatus());
//        assertEquals(symbols, inMemoryAccountStore.getWatchList(TEST_ACCOUNT_ID).getSymbols());
//    }
//
//    @Test
//    void canRemoveWatchListForTestAccount(){
//        givenWatchListForAccountExists();
//        assertFalse(inMemoryAccountStore.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
////        final var request = HttpRequest.DELETE("/");
////        final HttpResponse<Object> deleted = client.toBlocking().exchange(request);
//        var deleted = client.toBlocking().exchange(HttpRequest.DELETE("/"));
//        assertEquals(HttpStatus.NO_CONTENT, deleted.getStatus());
//        assertTrue(inMemoryAccountStore.getWatchList(TEST_ACCOUNT_ID).getSymbols().isEmpty());
//    }
//}

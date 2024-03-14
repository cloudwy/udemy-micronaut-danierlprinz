package com.wy.udemy.broker.wallet;

import static com.wy.udemy.broker.data.InMemoryAccountStore.ACCOUNT_ID;
import com.wy.udemy.broker.data.InMemoryAccountStore;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;

import java.util.Collection;
import java.util.List;

@Controller("/account/wallets")
public record WalletController(InMemoryAccountStore store) {
    public static final List<String> SUPPORTED_FIAT_CURRENCIES = List.of("EUR", "USD", "CHF", "GBP");

    @Get(produces = MediaType.APPLICATION_JSON)
    public Collection<Wallet> get(){
        return store.getWallets(ACCOUNT_ID);
    }

    @Post(value="/deposit", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Void> depositFiatMoney(@Body DepositFiatMoney deposit){
        // Option 1: Custon HttpResponse
        return HttpResponse.ok();
    }

    @Post(value = "/withdraw", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public void withdrawFiatMoney(@Body WithdrawFiatMoney withdraw){
        // Option2: Custom Error Processing
    }
}

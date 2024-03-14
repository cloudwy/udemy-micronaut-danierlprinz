package com.wy.udemy.broker.wallet;

import static com.wy.udemy.broker.data.InMemoryAccountStore.ACCOUNT_ID;

import com.wy.udemy.broker.api.RestApiResponse;
import com.wy.udemy.broker.data.InMemoryAccountStore;
import com.wy.udemy.broker.wallet.error.CustomError;
import com.wy.udemy.broker.wallet.error.FiatCurrencyNotSupportedException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

@Controller("/account/wallets")
public record WalletController(InMemoryAccountStore store) {
    private static final Logger LOG = LoggerFactory.getLogger(WalletController.class);
    public static final List<String> SUPPORTED_FIAT_CURRENCIES = List.of("EUR", "USD", "CHF", "GBP");

    @Get(produces = MediaType.APPLICATION_JSON)
    public Collection<Wallet> get(){
        return store.getWallets(ACCOUNT_ID);
    }

    @Post(value="/deposit", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<RestApiResponse> depositFiatMoney(@Body DepositFiatMoney deposit){
        // Option 1: Custon HttpResponse
        if (!SUPPORTED_FIAT_CURRENCIES.contains(deposit.symbol().value())){
            CustomError customError = new CustomError(HttpStatus.BAD_REQUEST.getCode(),
                    "UNSUPPORTED_FIAT_CURRENCY",
                    String.format("Only %s are supported", SUPPORTED_FIAT_CURRENCIES));
            return HttpResponse.badRequest().body(customError);
        }
        var wallet = store.depositToWallet(deposit);
        LOG.debug("Deposit to wallet: {}", wallet);
        return HttpResponse.ok().body(wallet); //two different responses
    }

    @Post(value = "/withdraw", consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public void withdrawFiatMoney(@Body WithdrawFiatMoney withdraw){
        // Option2: Custom Error Processing
        if (!SUPPORTED_FIAT_CURRENCIES.contains(withdraw.symbol().value())){
            throw new FiatCurrencyNotSupportedException(String.format("Only %s are supported", SUPPORTED_FIAT_CURRENCIES));
        }
    }
}

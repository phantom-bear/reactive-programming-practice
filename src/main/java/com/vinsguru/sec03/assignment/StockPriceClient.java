package com.vinsguru.sec03.assignment;

import com.vinsguru.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class StockPriceClient extends AbstractHttpClient {

    public Flux<Integer> getStockPrice() {
        return this.httpClient.get()
                .uri("/demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }
}

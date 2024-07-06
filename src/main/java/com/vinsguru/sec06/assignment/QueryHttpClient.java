package com.vinsguru.sec06.assignment;

import com.vinsguru.common.AbstractHttpClient;
import reactor.core.publisher.Flux;

public class QueryHttpClient extends AbstractHttpClient {

    public Flux<String> getOrderStream(){
        return this.httpClient.get()
                .uri("/demo04/orders/stream")
                .responseContent()
                .asString();
    }

}

package com.vinsguru.sec11.client;

import com.vinsguru.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClientResponse;

public class ExternalClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalClient.class);

    public record Product(String price,String product,String review){};

    private Mono<String> getInfo(String requestUrl) {
        return this.httpClient.get()
                .uri(requestUrl)
                .response(this::toResponse)
                .next()
                .publishOn(Schedulers.boundedElastic());
    }

    public Mono<String> getCountry(){
        String requestUrl = "/demo06/country" ;
        return this.getInfo(requestUrl);
    }

    public Mono<String> getProductById(int productID){
        String requestUrl = "/demo06/product/" + productID;
        return this.getInfo(requestUrl);
    }

    private Flux<String> toResponse(HttpClientResponse response, ByteBufFlux flux){
        return switch (response.status().code()){
            case 200 -> flux.asString();
            case 400 -> Flux.error(new ClientError());
            default -> Flux.error(new ServerError());
        };
    }



}

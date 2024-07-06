package com.vinsguru.sec09.assignment;

import com.vinsguru.common.AbstractHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ExternalClient extends AbstractHttpClient {

    private static final Logger log = LoggerFactory.getLogger(ExternalClient.class);

    public record Product(String price,String product,String review){};

    private Mono<String> getInfo(String requestUrl) {
        return this.httpClient.get()
                .uri(requestUrl)
                .responseContent()
                .asString()
                .next()
                .publishOn(Schedulers.boundedElastic());
    }

    private Mono<String> getPriceById(int productID){
        String requestUrl = "/demo05/price/" + productID;
        return this.getInfo(requestUrl);
    }

    private Mono<String> getProductById(int productID){
        String requestUrl = "/demo05/product/" + productID;
        return this.getInfo(requestUrl);
    }

    private Mono<String> getReviewById(int productID){
        String requestUrl = "/demo05/review/" + productID;
        return this.getInfo(requestUrl);
    }

    public Mono<Product> getProduct(int productId){
        return Mono.zip(getPriceById(productId),
                getProductById(productId),
                getReviewById(productId))
                .map(m -> new Product(m.getT1(),m.getT2(),m.getT3()));
    }

}

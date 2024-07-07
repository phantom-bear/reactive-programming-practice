package com.vinsguru.sec10.assignment.group;

import reactor.core.publisher.Flux;

import java.time.Duration;

public class PurchasedOrderGenerator {

    public static Flux<PurchaseOrder> generateOrders(){
        return Flux.interval(Duration.ofMillis(200))
                .map(i -> PurchaseOrder.generate());
    }
}

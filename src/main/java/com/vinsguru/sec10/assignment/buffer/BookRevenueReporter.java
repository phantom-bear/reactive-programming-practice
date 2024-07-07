package com.vinsguru.sec10.assignment.buffer;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BookRevenueReporter {

    Map<String, Integer> revenueMap = new HashMap<String, Integer>();

    public void populateRevenueMap() {
        BookStreamer.getBookOrders().bufferTimeout(3, Duration.ofSeconds(10))
                .map(bookOrders -> {
                    if (bookOrders == null || bookOrders.isEmpty()) {
                        return 0;
                    }
                    for (BookOrder bookOrder : bookOrders) {
                        int bookRevenue = this.revenueMap.getOrDefault(bookOrder.genre(), 0);
                        revenueMap.put(bookOrder.genre(), bookRevenue += bookOrder.price());
                    }
                    return 0;
                }).then().subscribe();
    }


    public void getRevenueFlux() {
        Flux.interval(Duration.ofSeconds(5)).map(l -> revenueMap.toString()).subscribe(Util.subscriber());
    }

}

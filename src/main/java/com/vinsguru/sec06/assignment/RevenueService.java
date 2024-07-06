package com.vinsguru.sec06.assignment;

import com.vinsguru.common.DefaultSubscriber;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RevenueService implements Subscriber<Product> {

    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
    private String name;
    Map<String, Integer> revenue = new HashMap<String, Integer>();

    public RevenueService(String name) {
        this.name = name;
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Product product) {
        log.info("revenue onNext: {}",  product);
        String category = product.getCategory();
        Integer price = product.getPrice();
        if (revenue.containsKey(category)) {
            revenue.compute(category, (k, totalPrice) -> totalPrice + price);
        }
        else{
            revenue.put(category, price);
        }

    }

    @Override
    public void onError(Throwable t) {
        log.error("error happened when calculating revenue : {}",t.getMessage(), t);
    }

    @Override
    public void onComplete() {
        log.info("revenue completed");
    }
}

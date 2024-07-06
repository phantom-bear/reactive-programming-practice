package com.vinsguru.sec06.assignment;

import com.vinsguru.common.DefaultSubscriber;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class InventoryService implements Subscriber<Product> {
    private static final Logger log = LoggerFactory.getLogger(DefaultSubscriber.class);
    private String name;
    Map<String, Integer> quantatyMap = new HashMap<String, Integer>();

    public InventoryService(String name) {
        this.name = name;
    }


    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(Product product) {
        log.info("Inventory onNext: {}",product);
        String category = product.getCategory();
        Integer productQuantity = product.getQuantity();
        if (quantatyMap.containsKey(category) && quantatyMap.get(category) != null && quantatyMap.get(category) >= productQuantity) {
            quantatyMap.compute(category, (k, quantity) -> quantity - productQuantity);
        }
        else{
            quantatyMap.put(category, 500);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        log.error("error happened when calculation item quantity: {}", throwable.getMessage(), throwable);
    }

    @Override
    public void onComplete() {
        log.info("completed calculation item quantity");
    }
}

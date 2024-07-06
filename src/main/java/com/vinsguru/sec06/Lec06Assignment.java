package com.vinsguru.sec06;

import com.vinsguru.common.Util;
import com.vinsguru.sec06.assignment.InventoryService;
import com.vinsguru.sec06.assignment.Product;
import com.vinsguru.sec06.assignment.QueryHttpClient;
import com.vinsguru.sec06.assignment.RevenueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

public class Lec06Assignment {
    private static final Logger log = LoggerFactory.getLogger(Lec06Assignment.class);

    public static void main(String[] args) {
        QueryHttpClient client = new QueryHttpClient();
        Flux<Product> orderString = client.getOrderStream().
        publish().refCount(2)
                .map(productString -> {
                    String [] split = productString.split(":");
                    Product product = new Product();
                    product.setItem(split[0]);
                    product.setCategory(split[1]);
                    product.setPrice(Integer.parseInt(split[2]));
                    product.setQuantity(Integer.parseInt(split[3]));
                    return product;
                }).doOnNext( o -> log.info("{}",o));
        orderString.subscribe(new RevenueService("rv1"));
        orderString.subscribe(new InventoryService("In1"));



        Util.sleepSeconds(100);
    }
}

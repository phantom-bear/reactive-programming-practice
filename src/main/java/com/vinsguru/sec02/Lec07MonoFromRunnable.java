package com.vinsguru.sec02;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class Lec07MonoFromRunnable {

    public static final Logger log = LoggerFactory.getLogger(Lec03MonoSubscribe.class);

    public static void main(String[] args) {
        getProductName(2)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getProductName(int productId){
        if (productId == 1){
            return Mono.fromSupplier(() -> Util.faker().commerce().productName());
        }
        return Mono.fromRunnable(() -> notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId){
        log.info("notify business on unavailable product: {}", productId);
    }
}

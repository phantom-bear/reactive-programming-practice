package com.vinsguru.sec11;

import com.vinsguru.common.Util;
import com.vinsguru.sec11.client.ExternalClient;
import com.vinsguru.sec11.client.ServerError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.util.retry.Retry;

import java.time.Duration;

public class Lec03ExternalServiceDemo {
    private static final Logger log = LoggerFactory.getLogger(Lec03ExternalServiceDemo.class);
    public static void main(String[] args) {


        Flux.just("a")
                .retry(1)
                .retry(2)
                .subscribe(Util.subscriber());

//        retry();

        Util.sleepSeconds(60);
    }

    private static void repeat(){
        var client = new ExternalClient();

        client.getCountry()
                .repeat()
                .takeUntil(c -> c.equalsIgnoreCase("canada"))
                .subscribe(Util.subscriber());
    }

    private static void retry(){
        var client = new ExternalClient();

        client.getProductById(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError(){
        return Retry.fixedDelay(20 , Duration.ofSeconds(1))
                .filter(ex -> ServerError.class.equals(ex.getClass()))
                .doBeforeRetry(re -> log.info("retrying {}", re.failure().getMessage()));
    }
}

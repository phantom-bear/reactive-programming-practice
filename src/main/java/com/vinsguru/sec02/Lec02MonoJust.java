package com.vinsguru.sec02;

import com.vinsguru.sec01.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    public static void main(String[] args) {

       var mono = Mono.just("vins");
       var subscriber = new SubscriberImpl();
       mono.subscribe(subscriber);

       subscriber.getSubscription().request(10);
//       adding these will have no effect as producer already set complete
        subscriber.getSubscription().request(10);
        subscriber.getSubscription().cancel();

    }

}

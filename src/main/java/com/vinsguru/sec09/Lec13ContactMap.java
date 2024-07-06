package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.assignment.ExternalClient;
import reactor.core.publisher.Flux;

public class Lec13ContactMap {

    public static void main(String[] args) {

        ExternalClient client = new ExternalClient();
        Flux.range(1, 10)
                .concatMap(client::getProduct)
                .subscribe(Util.subscriber());

        Util.sleepSeconds(20);

    }
}

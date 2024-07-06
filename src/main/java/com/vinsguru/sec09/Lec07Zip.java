package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.assignment.ExternalClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
/*
    Zip
    - we will subscribe to all the producers at the same time
    - all or nothing
    - all producers will have to emit an item
 */
public class Lec07Zip {

    record Car(String Body, String engine, String tires){}
    public static void main(String[] args) {
        Flux.zip(getBody(), getEngine(), getTires())
                .map(t -> new Car(t.getT1(), t.getT2(), t.getT3()))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(5);
    }

    private static Flux<String> getBody(){
        return Flux.range(1, 5)
                .map(i -> "body-" + i)
                .delayElements(Duration.ofMillis(100));
    }

    private static Flux<String> getEngine(){
        return Flux.range(1, 3)
                .map(i -> "engine-" + i)
                .delayElements(Duration.ofMillis(200));
    }

    private static Flux<String> getTires(){
        return Flux.range(1, 10)
                .map(i -> "tire-" + i)
                .delayElements(Duration.ofMillis(75));
    }


    public static class Lec08ZipAssignment {
        public static void main(String[] args) {

            var client = new ExternalClient();
            for (int i = 1; i <= 10; i++) {
                client.getProduct(i).subscribe(Util.subscriber());
            }

            Util.sleepSeconds(100);
        }
    }
}

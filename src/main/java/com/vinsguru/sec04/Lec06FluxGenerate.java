package com.vinsguru.sec04;

import com.vinsguru.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;


/*
    Flux generate
    - invokes the given lambda expression again and again based on downstream demand.
    - We can emit only one value at a time
    - will stop when complete method is invoked
    - will stop when error method is invoked
    - will stop downstream cancels
 */
public class Lec06FluxGenerate {

    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {
        assignmentLec01();
    }

    public static void demo(){
        Flux.generate(synchronousSink -> { // when using synchronous, it can emit max one item
                    log.info("invoked");
                    synchronousSink.next(1);
//            synchronousSink.next(2);
//            synchronousSink.complete();
                }).take(4)
                .subscribe(Util.subscriber());
    }

    public static void assignmentLec01(){
        Flux.generate(synchronousSink -> {
            String name = Util.faker().country().name();
            if (name.equalsIgnoreCase("canada")){
                synchronousSink.next(name);
                synchronousSink.complete();
            }
            else {
                synchronousSink.next(name);
            }

        }).subscribe(Util.subscriber());
    }

}

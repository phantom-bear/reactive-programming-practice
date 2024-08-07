package com.vinsguru.sec10;

import com.vinsguru.common.Util;
import com.vinsguru.sec10.assignment.group.OrderProcessingService;
import com.vinsguru.sec10.assignment.group.PurchaseOrder;
import reactor.core.publisher.Flux;

import java.time.Duration;


public class Lec06GroupFluxAssignment {

    public static void main(String[] args) {

        orderStream()
                .filter(OrderProcessingService.canProcess())
                .groupBy(PurchaseOrder::category)
                .flatMap(gf -> gf.transform(OrderProcessingService.getProcessor(gf.key())))
                .subscribe(Util.subscriber());

        Util.sleepSeconds(60);
    }


    private static Flux<PurchaseOrder> orderStream(){
        return Flux.interval(Duration.ofMillis(5))
                .map(i -> PurchaseOrder.generate());

    }

}

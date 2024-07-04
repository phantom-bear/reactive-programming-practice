package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class Lec01FluxJust {

    public static void main(String[] args) {
        Flux.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, "sam")
                .subscribe(Util.subscriber());
    }
}

package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import com.vinsguru.sec01.subscriber.SubscriberImpl;
import com.vinsguru.sec03.helper.NameGenerator;

public class Lec07FluxVsList {
    public static void main(String[] args) {

        // this way is less reactive, it gives result at all end which all name generated and populate the list
//        var list = NameGenerator.getNameList(10);
//        System.out.println(list);

        // this is more reactive, this can stream generated names one by one, and we can stop earlier if we find the
        // name we want, if using the traditional way it's not going to be supported
        var subscriber = new SubscriberImpl();
        NameGenerator.getNameFlux(10)
                .subscribe(subscriber);

        subscriber.getSubscription().request(3);
        subscriber.getSubscription().cancel();

    }
}

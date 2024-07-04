package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import com.vinsguru.sec03.client.ExternalServiceClient;

public class Lec08NonBlockingStreamMessages {

    public static void main(String[] args) {

        var client = new ExternalServiceClient();
        client.getNames()
                .subscribe(Util.subscriber("sub1"));

        client.getNames()
                .subscribe(Util.subscriber("sub2"));

        Util.sleepSeconds(6);
    }
}

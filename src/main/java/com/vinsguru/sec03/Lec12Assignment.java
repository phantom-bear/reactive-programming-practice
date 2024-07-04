package com.vinsguru.sec03;

import com.vinsguru.common.Util;
import com.vinsguru.sec03.assignment.StockPriceClient;
import com.vinsguru.sec03.assignment.StockPriceObserver;

public class Lec12Assignment {
    public static void main(String[] args) {
        var client = new StockPriceClient();
        var subscriber = new StockPriceObserver();
        client.getStockPrice()
                .subscribe(subscriber);
        Util.sleepSeconds(20);
    }
}

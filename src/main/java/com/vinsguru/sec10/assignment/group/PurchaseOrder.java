package com.vinsguru.sec10.assignment.group;

import com.vinsguru.common.Util;

public record PurchaseOrder(String item, String category, Integer price) {

    public static PurchaseOrder generate(){
        String item = Util.faker().commerce().productName();
        String category = Util.faker().commerce().department();
        Integer price = Util.faker().random().nextInt(30, 100);
        return new PurchaseOrder(item, category, price);
    }
}

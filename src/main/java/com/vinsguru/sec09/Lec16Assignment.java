package com.vinsguru.sec09;

import com.vinsguru.common.Util;
import com.vinsguru.sec09.applications.*;
import reactor.core.publisher.Mono;

import java.util.List;

public class Lec16Assignment {

    record UserInformation(Integer userId, String username, Integer balance, List<Order> orders){};

    public static void main(String[] args) {

//        // my way of doing this assignment, it works but not perfect
//        UserService.getUsers().flatMap(user -> {
//
//            Mono<Integer> balance = PaymentService.getUserBalance(user.id());
//
//            Mono<List<Order>> orderList = OrderService.getUserOrders(user.id()).collectList();
//
//            return Mono.just(new UserInformation(user.id(), user.name(), balance.block(), orderList.block()));
//        }).subscribe(Util.subscriber());

        // professor's way of doing this assignment, works perfectly
        UserService.getUsers()
                .flatMap(Lec16Assignment::getUserInformation)
                .subscribe(Util.subscriber());


        Util.sleepSeconds(10);

    }

    private static Mono<UserInformation> getUserInformation(User user) {

        return Mono.zip(
                PaymentService.getUserBalance(user.id()),
                OrderService.getUserOrders(user.id()).collectList()
        ).map(t -> new UserInformation(user.id(), user.name(), t.getT1(), t.getT2()));

    }
}

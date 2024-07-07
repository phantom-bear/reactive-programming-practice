package com.vinsguru.sec10;

import com.vinsguru.common.Util;
import com.vinsguru.sec10.assignment.buffer.BookRevenueReporter;

public class Lec02BufferAssignment {


    public static void main(String[] args) {

        BookRevenueReporter repo = new BookRevenueReporter();
        repo.populateRevenueMap();
        repo.getRevenueFlux();

        Util.sleepSeconds(100);
    }



}

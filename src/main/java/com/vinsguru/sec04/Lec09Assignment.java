package com.vinsguru.sec04;


import com.vinsguru.common.Util;
import com.vinsguru.sec04.assignment.FileReaderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;


public class Lec09Assignment {
    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    public static void main(String[] args) {
        var path = Path.of("src/main/resources/sec04/file.txt");
        FileReaderServiceImpl fileReaderService = new FileReaderServiceImpl();
        fileReaderService.readFile(path)
                .takeUntil(line -> line.equalsIgnoreCase("line17")).subscribe(Util.subscriber());

    }


}

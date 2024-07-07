package com.vinsguru.sec10;

import com.vinsguru.common.Util;
import com.vinsguru.sec10.assignment.window.FileWriter;
import com.vinsguru.sec10.assignment.window.RandomWordsGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class Lec04WindowAssignment {

    private static final Logger log = LoggerFactory.getLogger(Lec04WindowAssignment.class);
    public static void main(String[] args) {
        var counter =  new AtomicInteger(0);
        var fileNameFormat = "src/main/resources/sec10/file%d.txt";
        eventStream()
                .window(Duration.ofMillis(1800))
                .flatMap(flux -> FileWriter.create(flux, Path.of(fileNameFormat.formatted(counter.getAndIncrement()))))
                .subscribe();

        Util.sleep(Duration.ofSeconds(60));
    }
    private static Flux<String> eventStream(){
        return Flux.interval(Duration.ofMillis(500))
                .map(i -> "event-" + (i + 1));
    }
}

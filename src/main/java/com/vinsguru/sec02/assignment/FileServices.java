package com.vinsguru.sec02.assignment;

import reactor.core.publisher.Mono;

public interface FileServices {
    Mono<String> read(String filename);
    Mono<Void> write(String filename, String content);
    Mono<Void> delete(String filename);
}

package com.vinsguru.sec02.assignment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Path;


public class FileServicesImpl implements FileServices{
    private static final Logger log = LoggerFactory.getLogger(FileServicesImpl.class);
    private static final Path PATH = Path.of("src/main/resources/sec02");
    @Override
    public Mono<String> read(String filename) {
        return Mono.fromCallable(() -> Files.readString(PATH.resolve(filename)));
    }

    @Override
    public Mono<Void> write(String filename, String content) {

        return Mono.fromRunnable(() -> writeFileHelper(filename, content));
    }

    @Override
    public Mono<Void> delete(String filename) {
        return Mono.fromRunnable(() -> deleteFileHelper(filename));
    }

    private void writeFileHelper(String filename, String content) {
        try{
            Files.writeString(PATH.resolve(filename), content);
            log.info("created {} ", filename);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    private void deleteFileHelper(String filename) {
        try{
            Files.delete(PATH.resolve(filename));
            log.info("deleted {} ", filename);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


}

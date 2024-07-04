package com.vinsguru.sec04.assignment;

import com.vinsguru.sec04.Lec06FluxGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileReaderServiceImpl  implements FileReaderService {
    private static final Logger log = LoggerFactory.getLogger(Lec06FluxGenerate.class);

    @Override
    public Flux<String> readFile(Path path) {

        return Flux.generate(
                () -> openFile(path),
                this::readFile,
                this::closeFile
        );
    }

    private BufferedReader openFile(Path path) throws IOException {
        log.info("Opening file {}", path);
        return Files.newBufferedReader(path);
    }

    private BufferedReader readFile(BufferedReader bufferedReader, SynchronousSink<String> sink) {
        try {
            var line = bufferedReader.readLine();
            log.info("Reading line {}", line);
            if (Objects.isNull(line)){
                sink.complete();
            }
            else {
                sink.next(line);

            }
        }catch (Exception e){
            sink.error(e);
        }
        return bufferedReader;
    }

    private void closeFile(BufferedReader bufferedReader) {
        try {
            bufferedReader.close();
            log.info("closed file");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

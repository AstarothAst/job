package com.example.job.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@Slf4j
public class CustomJob extends GenericJob {

    int i = 0;

    @Override
    public void exec() {
        log.info("Trigger {}", i++);
    }

    @Override
    public Instant computeNextExecuteInstant(Instant lastExecution) {
        return Optional.ofNullable(lastExecution)
                .orElse(Instant.now())
                .plusSeconds(1);
    }
}

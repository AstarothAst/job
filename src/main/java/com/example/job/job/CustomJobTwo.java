package com.example.job.job;

import com.example.job.api.SpeedController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomJobTwo extends GenericJob {

    private final SpeedController speedController;

    private int i = 0;

    @Override
    public void exec() {
        log.info(">>>TWO {}", i++);
    }

    @Override
    public Instant computeNextExecuteInstant(Instant lastExecution) {
        return Optional.ofNullable(lastExecution)
                .orElse(Instant.now())
                .plusSeconds(10 - speedController.getSpeed());
    }
}

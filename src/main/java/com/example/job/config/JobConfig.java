package com.example.job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class JobConfig {

    @Bean
    public Executor taskExecutorPool() {
        return Executors.newScheduledThreadPool(10);
    }
}

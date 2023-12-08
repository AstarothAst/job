package com.example.job.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;

import java.time.Instant;
import java.util.concurrent.Executor;

@RequiredArgsConstructor
@Slf4j
public abstract class GenericJob implements SchedulingConfigurer {

    @Autowired private Executor taskExecutorPool;

    public abstract void exec();

    public abstract Instant computeNextExecuteInstant(Instant lastExecution);

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutorPool);
        Trigger trigger = triggerContext -> computeNextExecuteInstant(triggerContext.lastCompletion());
        Runnable task = this::exec;

        TriggerTask triggerTask = new TriggerTask(task, trigger);
        taskRegistrar.addTriggerTask(triggerTask);
    }
}
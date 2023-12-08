package com.example.job.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
@Slf4j
public abstract class PandaGenericScheduler implements SchedulingConfigurer, ApplicationListener<ApplicationReadyEvent> {


    private final Executor taskExecutorPool;

    private AtomicBoolean isApplicationStarted = new AtomicBoolean(false);
    private boolean isNeedRunSchedulerOnStartup = false;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutorPool);
        Trigger trigger = getTrigger();
        Runnable task = () -> {
            log.info("Trigger");
        };
        TriggerTask triggerTask = new TriggerTask(task, trigger);

        taskRegistrar.addTriggerTask(triggerTask);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        isApplicationStarted.set(true);
        if (isNeedRunSchedulerOnStartup) {

        }
    }

    private Trigger getTrigger() {
        return triggerContext -> {
            Instant lastCompletion = triggerContext.lastCompletion();
            return Instant.now().plusSeconds(2);
        };
    }
}
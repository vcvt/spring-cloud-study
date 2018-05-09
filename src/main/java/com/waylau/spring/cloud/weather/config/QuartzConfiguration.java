package com.waylau.spring.cloud.weather.config;

import com.waylau.spring.cloud.weather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author smmit
 * @date 2018-05-09
 */
@Configuration
public class QuartzConfiguration {

    @Bean
    public JobDetail weatherDataSyncJob() {
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob").storeDurably().build();
    }

    @Bean
    public Trigger weatherDataSyncTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1800).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJob()).withIdentity("weatherDataSyncTrigger").withSchedule(scheduleBuilder).build();
    }
}

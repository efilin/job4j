package ru.job4j.sqlruparser;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main implements Job {
    PageParser pageParser = new PageParser();
    StoreSQL storeSQL = new StoreSQL();
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());


    public static void main(String[] args) {
        PageParser pageParser = new PageParser();
        StoreSQL storeSQL = new StoreSQL();
        if (storeSQL.getNumberOfStarts() > 1) {
            pageParser.nextStart();
        } else {
            LOG.info("detects first start of program");
            pageParser.firstStart();
        }
        storeSQL.setVacancyList(pageParser.getVacancyList());
        storeSQL.addVacancyList(storeSQL.getVacancyList());
        pageParser.clearVacancyList();
        storeSQL.clearVacancyList();

        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = loader.getResourceAsStream("app3.properties")) {
            properties.load(is);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        String cronExp = properties.getProperty("cron.time");

        JobDetail job = JobBuilder.newJob(Main.class).build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(cronExp))
                .build();
        try {
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOG.info("Execute scheduler job");
        pageParser.nextStart();
        storeSQL.setVacancyList(pageParser.getVacancyList());
        storeSQL.addVacancyList(storeSQL.getVacancyList());
        pageParser.clearVacancyList();
        storeSQL.clearVacancyList();
    }
}

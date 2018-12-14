package ru.job4j.sqlruparser;


        import org.quartz.*;
        import org.quartz.impl.StdSchedulerFactory;

        import org.apache.logging.log4j.Logger;
        import org.apache.logging.log4j.LogManager;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Properties;

public class Main implements Job {

    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());
    private static final String DEFAULT_CONFIG = "app3.properties";
    private String config;

    private final PageParser pageParser = new PageParser();

    public Main(String config) {
        this.config = config;
    }

    public static void main(String[] args) {


        String config = DEFAULT_CONFIG;
        if (args.length == 0) {
            LOG.info("load default properties");
        } else {
            config = args[0];
            LOG.info("load properties from args");
        }
        Properties properties = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (InputStream in = loader.getResourceAsStream(config)) {
            properties.load(in);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        Main main = new Main(config);

        main.firstStart(config);

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

    public void firstStart(String config) {
        StoreSQL storeSQL = new StoreSQL(config);
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
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        LOG.info("Execute scheduler job");
        StoreSQL storeSQL = new StoreSQL(config);
        pageParser.nextStart();
        storeSQL.setVacancyList(pageParser.getVacancyList());
        storeSQL.addVacancyList(storeSQL.getVacancyList());
        pageParser.clearVacancyList();
        storeSQL.clearVacancyList();
    }
}

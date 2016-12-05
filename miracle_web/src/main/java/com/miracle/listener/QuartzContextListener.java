package com.miracle.listener;

import com.miracle.util.ExceptionUtil;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 * Created by yangchao2014 on 2016/11/28.
 */
public class QuartzContextListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        Logger logger = Logger.getLogger(this.getClass());
        SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = gSchedulerFactory.getScheduler();
            if (scheduler != null) {
                scheduler.shutdown();
                System.out.println("Quartz Shut Down");
            }
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            logger.error(ExceptionUtil.parse(e));
        } catch (SchedulerException e) {
            logger.error(ExceptionUtil.parse(e));
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
    }

}

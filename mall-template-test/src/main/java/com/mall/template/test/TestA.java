package com.mall.template.test;

/**
 * Hello world!
 *
 */

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestA {

    static ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);

    static ScheduledExecutorService singleThreadPool = Executors.newSingleThreadScheduledExecutor();

    private final static String confPath = "D:\\workspace\\mall-template-api\\mall-template-test\\src\\main\\resources\\logback.xml";

    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {

        System.out.println("start ----");
        initLogback();        // 初始化logback

        ARunnable aRunnable1 = new ARunnable("a-n-1", false, -1);
        ARunnable aRunnable2 = new ARunnable("a-n-2", true, 5);

        BRunnable bRunnable1 = new BRunnable("b-n-1", false, -1);
        BRunnable bRunnable2 = new BRunnable("b-n-2", true, 7);

//        testUseScheduledThreadPool(aRunnable1, aRunnable2);

//        testUseThread(bRunnable1,bRunnable2);

        testFixedThreadPool(bRunnable1,bRunnable2);
    }

    /**加载logback配置信息*/
    public static void initLogback() {
        //加载 logback配置信息
        try {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            configurator.doConfigure(confPath);
            StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        } catch (JoranException e) {
            e.printStackTrace();
            System.out.println("sync logback.xml error! "+ e.getMessage());
            System.exit(0);
        }
    }

    /**使用线程池ScheduledThreadPool测试*/
    public static void testUseScheduledThreadPool(Runnable aRunnable1, Runnable aRunnable2){
        threadPool.scheduleAtFixedRate(aRunnable1,1,20, TimeUnit.MILLISECONDS);
        threadPool.scheduleAtFixedRate(aRunnable2,1,20, TimeUnit.MILLISECONDS);
    }

    /**使用Thread线程测试*/
    public static void testUseThread(Runnable runnable1, Runnable runnable2){
        new Thread(runnable1).start();
        new Thread(runnable2).start();
    }

    public static void testFixedThreadPool(Runnable runnable1, Runnable runnable2){
        fixedThreadPool.execute(runnable1);
        fixedThreadPool.execute(runnable2);
    }

    /**线程循环调度*/
    public static class ARunnable implements Runnable {

        private final static Logger LOGGER = LoggerFactory.getLogger(ARunnable.class);

        String name;        //线程名称

        int count = 0;      //计数

        boolean isInterrupt = false;    //是否内部中断线程

        int interruptCount ;            //count达到  interruptCount  时中断线程

        public ARunnable(){ }

        public ARunnable(String name, boolean isInterrupt, int interruptCount){
            this.name = name;
            this.isInterrupt = isInterrupt;
            this.interruptCount = interruptCount;
        }

        @Override
        public void run() {
            if(isInterrupt && interruptCount == count){
                Thread.currentThread().interrupt();
                LOGGER.warn("A interrupt ... name:"+name+",count:"+count++);
            }else{
                LOGGER.warn("A name:"+name+",count:"+count++);
            }
        }
    }

    /**内部循环执行*/
    public static class BRunnable implements Runnable {

        private final static Logger LOGGER = LoggerFactory.getLogger(BRunnable.class);

        String name;        //线程名称

        int count = 0;      //计数

        boolean isInterrupt = false;    //是否内部中断线程

        int interruptCount ;            //count达到  interruptCount  时中断线程

        public BRunnable(){ }

        public BRunnable(String name, boolean isInterrupt, int interruptCount){
            this.name = name;
            this.isInterrupt = isInterrupt;
            this.interruptCount = interruptCount;
        }

        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(isInterrupt && interruptCount == count){
                    Thread.currentThread().interrupt();
                    LOGGER.warn("B interrupt ...name:"+name+",count:"+count++);
                }
                LOGGER.warn("B name:"+name+",count:"+count++);
            }
        }
    }


}

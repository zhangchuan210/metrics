package com.cn.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 描述: TODO
 * 日期:  2018-03-29 16:23
 *
 * @author: ZC
 * @since 1.0
 */
public class TimerTest {
    public static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        Timer timer = registry.timer(MetricRegistry.name(TimerTest.class,"get-latency"));
        Timer.Context ctx;
        while(true){
            ctx = timer.time();
            Thread.sleep(random.nextInt(1000));
            ctx.stop();
        }
    }

}

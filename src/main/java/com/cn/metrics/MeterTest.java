package com.cn.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 描述: TODO
 * 日期:  2018-03-29 16:00
 *
 * @author: ZC
 * @since 1.0
 */
public class MeterTest {
    public static Random random = new Random();

    public static void request(Meter meter){
        System.out.println("request");
        meter.mark();
    }

    public static void request(Meter meter, int n){
        while(n > 0){
            request(meter);
            n--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        Meter meterTps = registry.meter(MetricRegistry.name(MeterTest.class,"request","tps"));
        while(true){
            request(meterTps, random.nextInt(5));
            Thread.sleep(1000);
        }
    }
}

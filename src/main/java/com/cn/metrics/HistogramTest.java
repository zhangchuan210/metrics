package com.cn.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.ExponentiallyDecayingReservoir;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 描述: TODO
 * 日期:  2018-03-29 16:17
 *
 * @author: ZC
 * @since 1.0
 */
public class HistogramTest {
    public static Random random = new Random();

    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        Histogram histogram = new Histogram(new ExponentiallyDecayingReservoir());
        registry.register(MetricRegistry.name(HistogramTest.class, "request", "histogram"), histogram);
        while(true){
            Thread.sleep(1000);
            histogram.update(random.nextInt(100000));
        }
    }

}

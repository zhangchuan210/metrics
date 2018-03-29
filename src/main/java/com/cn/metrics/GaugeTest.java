package com.cn.metrics;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * 描述: TODO
 * 日期:  2018-03-29 15:48
 *
 * @author: ZC
 * @since 1.0
 */
public class GaugeTest {
    public static Queue<String> q = new LinkedList<String>();
    public static void main(String[] args) throws InterruptedException {
        MetricRegistry registry = new MetricRegistry();
        ConsoleReporter reporter = ConsoleReporter.forRegistry(registry).build();
        reporter.start(1, TimeUnit.SECONDS);
        registry.register(MetricRegistry.name(GaugeTest.class, "queue", "size"),
                new Gauge<Integer>() {
                    public Integer getValue() {
                        return q.size();
                    }
                });
        while(true){
            Thread.sleep(1000);
            q.add("Job-xxx");
        }
    }
}

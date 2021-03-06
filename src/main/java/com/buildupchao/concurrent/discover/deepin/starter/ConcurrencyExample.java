package com.buildupchao.concurrent.discover.deepin.starter;

import lombok.extern.slf4j.Slf4j;

/**
 * @author buildupchao
 * @date 2018/6/22
 * @since JDK1.8
 */
@Slf4j
public class ConcurrencyExample {

    private static final long count = 10_000L;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
           int a = 0;
           for (long i = 0; i < count; i++) {
               a += 5;
           }
        });
        thread.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        log.info("concurrency: {}ms, b={}", time, b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        log.info("serial: {}ms, b={}, a={}", time, b, a);
    }
}

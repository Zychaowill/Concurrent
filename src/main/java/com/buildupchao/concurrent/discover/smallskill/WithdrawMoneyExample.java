package com.buildupchao.concurrent.discover.smallskill;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author buildupchao
 * @date: 2017/4/26 02:56
 * @since JDK 1.8
 */
public class WithdrawMoneyExample {
    /**
     * 测试转账的main方法
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Account src = new Account(10000);
        Account target = new Account(10000);
        CountDownLatch countDownLatch = new CountDownLatch(9999);
        for (int i = 0; i < 9999; i++) {
            new Thread(() -> {
                src.transactionToTarget(1, target);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println("src=" + src.getBalance());
        System.out.println("target=" + target.getBalance());
    }

    /**
     * 账户类
     */
    static class Account {
        public Account(Integer banalce) {
            this.balance = banalce;
        }

        private Integer balance;

        public void transactionToTarget(Integer money, Account target) {//转账方法
            Allocator.getInstance().apply(this, target);
            this.balance -= money;
            target.setBanalce(target.getBalance() + money);
            Allocator.getInstance().release(this, target);
        }

        public Integer getBalance() {
            return balance;
        }

        public void setBanalce(Integer banalce) {
            this.balance = banalce;
        }
    }

    /**
     * 单例锁类
     */
    static class Allocator {
        private Allocator() {
        }

        private List<Account> locks = new ArrayList<>();

        public synchronized void apply(Account src, Account tag) {
            while (locks.contains(src) || locks.contains(tag)) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                }
            }
            locks.add(src);
            locks.add(tag);
        }

        public synchronized void release(Account src, Account tag) {
            locks.remove(src);
            locks.remove(tag);
            this.notifyAll();
        }

        public static Allocator getInstance() {
            return AllocatorSingle.install;
        }

        static class AllocatorSingle {
            public static Allocator install = new Allocator();
        }
    }
}
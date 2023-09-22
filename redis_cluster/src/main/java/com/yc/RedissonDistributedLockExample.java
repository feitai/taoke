package com.yc;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissonDistributedLockExample {
    public static void main(String[] args) {
        // 配置Redisson客户端
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379"); // 连接到Redis服务器

        // 创建Redisson客户端实例
        RedissonClient redisson = Redisson.create(config);

        // 银行账户示例
        BankAccount account = new BankAccount();

        // 模拟多个线程并发存款和取款
        Runnable depositTask = () -> {
            RLock lock = null;
            try {
                // 获取锁，保证只有一个线程能够执行存款操作
                lock = redisson.getLock("accountLock");
                lock.lock();

                // 模拟存款
                account.deposit(100);

                System.out.println(Thread.currentThread().getName() + " deposited 100. New balance: " + account.getBalance());
            } finally {
                // 释放锁
                lock.unlock();
            }
        };

        Runnable withdrawTask = () -> {
            RLock lock = null;
            try {
                // 获取锁，保证只有一个线程能够执行取款操作
                lock = redisson.getLock("accountLock");
                lock.lock();

                // 模拟取款
                account.withdraw(50);

                System.out.println(Thread.currentThread().getName() + " withdrew 50. New balance: " + account.getBalance());
            } finally {
                // 释放锁
                lock.unlock();
            }
        };

        // 创建多个线程来模拟并发操作
        Thread depositThread1 = new Thread(depositTask, "Depositor 1");
        Thread depositThread2 = new Thread(depositTask, "Depositor 2");
        Thread withdrawThread1 = new Thread(withdrawTask, "Withdrawal 1");
        Thread withdrawThread2 = new Thread(withdrawTask, "Withdrawal 2");

        // 启动线程
        depositThread1.start();
        depositThread2.start();
        withdrawThread1.start();
        withdrawThread2.start();

        // 等待所有线程执行完成
        try {
            depositThread1.join();
            depositThread2.join();
            withdrawThread1.join();
            withdrawThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭Redisson客户端
        redisson.shutdown();
    }
}

class BankAccount {
    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }
}

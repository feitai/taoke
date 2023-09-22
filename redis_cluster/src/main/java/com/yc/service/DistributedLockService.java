package com.yc.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistributedLockService {

    @Autowired
    private RedissonClient redissonClient;

    public void performTaskWithLock() {
        // 获取分布式锁
        RLock lock = redissonClient.getLock("myLock");

        try {
            // 尝试获取锁，最长等待10秒，锁的有效期30秒
            boolean isLocked = lock.tryLock(10, 30, java.util.concurrent.TimeUnit.SECONDS);

            if (isLocked) {
                // 成功获取锁，执行需要加锁的操作
                System.out.println("Task is running with the lock...");
                Thread.sleep(3000); // 模拟执行任务
            } else {
                System.out.println("Failed to acquire lock.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}

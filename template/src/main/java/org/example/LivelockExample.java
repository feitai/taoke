package org.example;

public class LivelockExample {
    static class SharedResource {
        private boolean flag = true;

        public synchronized void doActionA() {
            while (flag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            System.out.println("Action A performed.");
            flag = true;
            notify();
        }

        public synchronized void doActionB() {
            while (!flag) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            System.out.println("Action B performed.");
            flag = false;
            notify();
        }
    }

    public static void main(String[] args) {
        final SharedResource resource = new SharedResource();

        Thread thread1 = new Thread(() -> {
            while (true) {
                resource.doActionA();
                try {
                    Thread.sleep(100); // 模拟一些处理时间
                } catch (InterruptedException e) {
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                resource.doActionB();
                try {
                    Thread.sleep(100); // 模拟一些处理时间
                } catch (InterruptedException e) {
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

package net.javaguides.DeadLock;

public class DeadlockDemo {

    private static final Object lockA = new Object();
    private static final Object lockB = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("T1: Holding lockA");
                sleep();
                synchronized (lockB) {
                    System.out.println("T1: Acquired lockB");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("T2: Holding lockB");
                sleep();
                synchronized (lockA) {
                    System.out.println("T2: Acquired lockA");
                }
            }
        });

        t1.start();
        t2.start();
    }

    private static void sleep() {
        try { Thread.sleep(100); } catch (Exception ignored) {}
    }
}

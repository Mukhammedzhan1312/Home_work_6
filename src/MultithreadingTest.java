public class MultithreadingTest {
    public static void main(String[] args) {
        Runnable task = () -> {
            ConfigurationManager configManager = ConfigurationManager.getInstance();
            System.out.println("Thread " + Thread.currentThread().getName() + " retrieved instance: " + configManager);
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


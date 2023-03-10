import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lesson5Concurrent {
    public static long count = 0; // The total number of characters in both .java and .class files.
    private static final Lock countLock = new ReentrantLock(); // Lock for --ReentrantLock option.
    private static AtomicLong nextCount = new AtomicLong(0); // AtomicLong for --AtomicLong option.

    public static void main(String[] args) throws InterruptedException {
        // Check parameters.
        if (args.length < 2) {
            System.out.println("Invalid parameter! (Too few)");
        } else if (args.length > 3) {
            System.out.println("Invalid parameter! (Too many)");
        }
        // Receive parameters.
        int numProcessor = 0;
        //  0 (default) -- no locking
        //  1           -- ReentrantLock
        // -1           -- AtomicLong
        int lockType = 0;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--ReentrantLock")) {
                lockType = 1;
            } else if (args[i].equals("--AtomicLong")) {
                lockType = -1;
            } else if (args[i].equals("--num-threads")) {
                numProcessor = Integer.parseInt(args[++i]);
            } else {
                System.out.printf("Invalid parameter! (%s)\n", args[i]);
            }
        }

        // Start threads.
        if (numProcessor > 0) {
            // Initialize a fixed thread pool with specified number of processors.
            ExecutorService executor = Executors.newFixedThreadPool(numProcessor);
            // Initialize the first Runnable task.
            Runnable task1 = createTask("Lesson5Concurrent.java", lockType);
            // Initialize the second Runnable task.
            Runnable task2 = createTask("Lesson5Concurrent.class", lockType);
            // Execute tasks using the executor.
            executor.execute(task1);
            executor.execute(task2);
            Thread.sleep(1000); // Wait until both tasks complete.
            System.out.println(
                    "The number of characters in the .java and the .class files is " + count
            );
        } else {
            System.out.println("Invalid parameter! (num-threads <= 0)");
        }
    }

    /**
     * Create a Runnable task which counts the number of characters in the specified file
     * using specified lock mode.
     * @param pathStr - the file path.
     * @param lockType - the lock mode.
     * @return the resulting Runnable task.
     */
    private static Runnable createTask(String pathStr, int lockType) {
        Runnable task = null;
        switch (lockType) {
            //  0 (default) -- no locking
            case 0 -> task = () -> {
                // Initialize a process to count the number of characters in the file.
                Process process;
                try { process = new ProcessBuilder("wc", "-m", pathStr).start(); }
                catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                // Read the character count in the file.
                try (Scanner scanner = new Scanner(process.getInputStream())) {
                    if (scanner.hasNextLong()) { count += scanner.nextLong(); }
                }
            };
            //  1 -- ReentrantLock
            case 1 -> task = () -> {
                Process process;
                try { process = new ProcessBuilder("wc", "-m", pathStr).start(); }
                catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                try (Scanner scanner = new Scanner(process.getInputStream())) {
                    if (scanner.hasNextLong()) {
                        countLock.lock();
                        try { count += scanner.nextLong(); }
                        finally { countLock.unlock(); }
                    }
                }
            };
            // -1 -- AtomicLong
            case -1 -> task = () -> {
                Process process;
                try { process = new ProcessBuilder("wc", "-m", pathStr).start(); }
                catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
                try (Scanner scanner = new Scanner(process.getInputStream())) {
                    if (scanner.hasNextLong()) {
                        count = nextCount.accumulateAndGet(scanner.nextLong(), Long::sum);
                    }
                }
            };
        }
        return task;
    }
}

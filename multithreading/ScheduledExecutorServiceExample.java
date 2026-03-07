import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Execute once after 5 second delay
        scheduler.schedule(() ->
                        System.out.println("Task executed after 5 second delay!"),
                5, TimeUnit.SECONDS);

        // Execute repeatedly every 5 seconds
        scheduler.scheduleAtFixedRate(() ->
                        System.out.println("FixedRate: Task executed every 5 seconds!"),
                5, 5, TimeUnit.SECONDS);

        // Execute with fixed delay between tasks
        scheduler.scheduleWithFixedDelay(() ->
                        System.out.println("FixedDelay: Task executed with 5 second delay!"),
                5, 5, TimeUnit.SECONDS);

        // Shutdown after 20 seconds
        scheduler.schedule(() -> {
            System.out.println("Initiating shutdown...");
            scheduler.shutdown();
        }, 20, TimeUnit.SECONDS);
    }
}
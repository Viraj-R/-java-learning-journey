import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {

        // 1. runAsync - no return value
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("Task 1 running in: " +
                    Thread.currentThread().getName());
        });

        // 2. supplyAsync - with return value
        // 3. thenApply - transform result
        // 4. thenAccept - consume result
        CompletableFuture<Void> future2 = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("Fetching data...");
                    return "Hello from CompletableFuture!";
                })
                .thenApply(result -> {
                    System.out.println("Processing: " + result);
                    return result.toUpperCase();
                })
                .thenAccept(result -> {
                    System.out.println("Final result: " + result);
                });

        // 5. exceptionally - handle exception
        CompletableFuture<Void> future3 = CompletableFuture
                .supplyAsync(() -> {
                    throw new RuntimeException("Something went wrong!");
                })
                .exceptionally(error -> {
                    System.out.println("Error caught: " + error.getMessage());
                    return "Default Value";
                })
                .thenAccept(result -> {
                    System.out.println("Recovered with: " + result);
                });

        // Wait for all futures to complete
        CompletableFuture.allOf(future1, future2, future3).join();

        System.out.println("Main thread done!");
    }
}
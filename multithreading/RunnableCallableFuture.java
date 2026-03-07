import java.util.concurrent.*;

public class RunnableCallableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
      Future<Integer> future =  executorService.submit(() -> {
          try {
              Thread.sleep(2000);
          } catch (InterruptedException e) {
              throw new RuntimeException(e);
          }
          System.out.println("Hello");
              return 42;
      });

       Thread.sleep(1000);
      future.cancel(false);
        System.out.println(future.isCancelled());
        System.out.println(future.isDone());
        executorService.shutdown();
//        Integer i = null;
//        try{
//                i = future.get( 1,TimeUnit.SECONDS);
//            System.out.println(i);
//        }catch (InterruptedException | TimeoutException| ExecutionException e){
//            System.out.println("Exception occured " + e);
//        }

        //        // Example 2
//
//        Future<Integer> submit = executor.submit(() -> 1 + 2);
//        Integer i = submit.get();
//        System.out.println("SUm is :" + i);
//        executor.shutdown();

        //Example 1
//        Future<?> future = executor.submit(() -> System.out.println("Hello"));
//        executor.shutdown();
    }
}

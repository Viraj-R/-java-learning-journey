
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsFramework {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis() ;
//        Thread[] threads = new Thread[9];
        ExecutorService executor = Executors.newFixedThreadPool(9);
        for (int i = 1; i < 10 ; i++){
            int finalI = i;
           executor.submit(() ->{
               long result = factorial(finalI);
               System.out.println(result);
           }) ;

        }

        executor.shutdown();
//         for (Thread thread : threads){
//             try{
//                 thread.join();
//             }catch (Exception e){
//                 Thread.currentThread().interrupt();
//             };
//         }

        try{
               while(! executor.awaitTermination( 10 , TimeUnit.MILLISECONDS)){
                   System.out.println("waiting");
               }
        }catch (Exception e){
             Thread.currentThread().interrupt();
        }
        System.out.println("Total Time  :" +(System.currentTimeMillis() - startTime));
    }

    private static long factorial(int n ){
        try{
            Thread.sleep(1000);
        }catch (Exception e){
              Thread.currentThread().interrupt();
        }
        long result = 1 ;
        for (int i = 1; i<= n; i++){
            result *= i;
        }
        return result;
    };
}

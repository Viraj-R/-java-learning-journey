import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class countDownLatchExample {
    public static void main(String[] args) throws InterruptedException {

        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        latch.await();
        System.out.println("Main Thread");
        executorService.shutdown();
    }
}
    class DependentService implements Runnable{

        private final CountDownLatch latch;

        public DependentService(CountDownLatch latch){
            this.latch = latch;
        }

        @Override
        public void run() {
             try {
                 System.out.println(Thread.currentThread().getName() + " Service Started");
                 Thread.sleep(2000);
             }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
             }finally {
                  latch.countDown();
             }

    };


}

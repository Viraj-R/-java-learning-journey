import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        int numberOfSubsystems = 4;
        CyclicBarrier barrier = new CyclicBarrier(numberOfSubsystems, new Runnable() {
            @Override
            public void run() {
                System.out.println("ALl subsytsems are up and runing . System startup complete");

            }
        });

        Thread webServerThread = new Thread( new Subsystem( "Web Server" , 2000 , barrier));
        Thread dataBaseThread = new Thread( new Subsystem( "dataBaseThread" , 4000 , barrier));
        Thread cacheThread = new Thread( new Subsystem( "cacheThread" , 3000 , barrier));
        Thread messagingServiceThread = new Thread( new Subsystem( "messagingServiceThread" , 3500 , barrier));

        webServerThread.start();
        dataBaseThread.start();
        cacheThread.start();
        messagingServiceThread.start();

    }
}

class Subsystem implements Runnable {
    private String name;
    private int initializationTime ;
    private CyclicBarrier barrier;

    public Subsystem( String name , int initializationTime , CyclicBarrier barrier){
        this.name = name;
        this.initializationTime = initializationTime;
        this.barrier = barrier;
    }


    @Override
    public void run() {
        try{
            System.out.println(name + " initialization started");
            Thread.sleep(initializationTime);
            System.out.println(name + " initialization complete .");
            barrier.await();
        } catch (InterruptedException e){
             e.printStackTrace();
        } catch (BrokenBarrierException e) {
            System.out.println(name + " barrier Broken");
            e.printStackTrace();
        }
    }
}


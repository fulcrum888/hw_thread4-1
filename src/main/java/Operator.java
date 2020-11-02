import java.util.Queue;

public class Operator extends Thread {
    private Queue queue;
    private int callDealTime;
    private CallCenter callCenter;

    public Operator(String name, int callDealTime, Queue queue, CallCenter callCenter) {
        super(name);
        this.callDealTime = callDealTime;
        this.queue = queue;
        this.callCenter = callCenter;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is online");
        while ((queue.poll() != null) || (callCenter.isOperating())) {
            try {
                System.out.println(Thread.currentThread().getName() + " deals call");
                Thread.sleep(callDealTime);
                System.out.println(Thread.currentThread().getName() + " is free");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " went offline");
    }
}

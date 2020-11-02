import java.util.Queue;

public class Caller extends Thread {
    private int callsInterval;
    private int callsCount;
    private Queue<Call> queue;
    private CallCenter callCenter;

    public Caller(int callsInterval, int callsCount, Queue<Call> queue, CallCenter callCenter) {
        this.callsInterval = callsInterval;
        this.callsCount = callsCount;
        this.queue = queue;
        this.callCenter = callCenter;
    }

    @Override
    public void run () {
        for (int i = 0; i < callsCount; i++) {
            System.out.printf("Calling... (%d)\n", i+1);
            queue.offer(new Call());
            try {
                Thread.sleep(callsInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        callCenter.setOperating(false);
    }
}

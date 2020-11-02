import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    final static int CALLS_INTERVAL = 1000;
    final static int CALLS_COUNT = 100;
    final static int MIN_CALL_DEAL_TIME = 3000;
    final static int MAX_CALL_DEAL_TIME = 4000;
    final static int OPERATORS_COUNT = 5;

    public static void main(String[] args) throws InterruptedException {
        Queue<Call> queue = new ConcurrentLinkedQueue<>();
        CallCenter callCenter = new CallCenter();
        Caller caller = new Caller(CALLS_INTERVAL, CALLS_COUNT, queue, callCenter);
        Operator[] operators = new Operator[OPERATORS_COUNT];

        caller.start();
        for (int i = 0; i < OPERATORS_COUNT; i++) {
            Random random = new Random();
            operators[i] = new Operator("Operator " + (i+1),
                    MIN_CALL_DEAL_TIME + random.nextInt(MAX_CALL_DEAL_TIME - MIN_CALL_DEAL_TIME),
                    queue, callCenter);
            operators[i].start();
        }

        caller.join();
        for (Operator operator : operators) {
            operator.join();
        }
    }
}

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Customer implements Runnable{
    private final LinkedBlockingQueue<Order> orderQueue;
    private final Random rand = new Random();
    Customer(LinkedBlockingQueue<Order> orderQueue){
        this.orderQueue = orderQueue;
    }
    @Override
    public void run() {
        while(true){
            int weightAdded = rand.nextInt(41) + 10;
            String destination = rand.nextBoolean() ? "Gotham" : "Atlanta";
            Order order = new Order(weightAdded, destination);
            try {
                orderQueue.put(order);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

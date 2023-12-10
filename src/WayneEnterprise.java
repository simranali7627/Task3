import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WayneEnterprise implements Runnable{

    int MAX_CARGO = 300;
    int MIN_CARGO = 50;
    int noOfTrips = 0;
    int consecutiveCancelledOrders = 0;
    int totalRevenueGenerated = 0;
    LinkedBlockingQueue<Order> orderQueue;
    WayneEnterprise(LinkedBlockingQueue<Order> orderQueue){
        this.orderQueue = orderQueue;
    }
    @Override
    public void run() {
        while(totalRevenueGenerated < 10000){
            Order order = null;
            try {
                order = orderQueue.poll(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(order != null){
                // since the ship can carry within range 50 - 300
                int weightAdded = Math.max(order.getCargoWeight(), MIN_CARGO);
                weightAdded = Math.min(order.getCargoWeight(), MAX_CARGO);
                if(order.getDestination().equals("Gotham")){

                    noOfTrips++;
                    totalRevenueGenerated += (noOfTrips / 2)  * 1000;
                    try {
                        orderQueue.put(new Order(weightAdded, "Atlanta"));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    consecutiveCancelledOrders = 0;
                }
                else{
                    try {
                        orderQueue.put(new Order(weightAdded, "Gotham"));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
            else{
                consecutiveCancelledOrders++;
                totalRevenueGenerated -= consecutiveCancelledOrders * 250;
                if(consecutiveCancelledOrders >= 3){
                    System.out.println("Customer left due to 3 consecutive cancellations : ");
                    break;
                }
            }
            System.out.println("Total Revenue generated : " + totalRevenueGenerated)  ;
            if(noOfTrips % 5 == 0){
                System.out.println("Ship going for maintenance");
                try {
                    Thread.sleep(3600);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println("The ship has reached the target." + order.getDestination());

        }
        System.out.println("No of trips made -> "+ noOfTrips);
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

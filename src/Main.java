import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

        WayneEnterprise simulation = new WayneEnterprise(orderQueue);
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        for(int i = 0; i < 7; i++){
            executorService.submit(new Customer(orderQueue));
        }

        for(int i = 0; i < 5; i++){
            executorService.submit(new WayneEnterprise(orderQueue));
        }
        executorService.shutdown();
        System.out.println("The Simulation has ended");

    }
}
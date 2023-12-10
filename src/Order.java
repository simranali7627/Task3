import java.util.Random;

public class Order {

    private final String destination;
    private final int cargoWeight;
    public Order(){
        Random rand = new Random();
        this.cargoWeight = rand.nextInt(41) + 10;
        this.destination = "Gotham";

    }
    public Order(int cargoWeight, String destination){
        this.cargoWeight = cargoWeight;
        this.destination = destination;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public String getDestination() {
        return destination;
    }



    @Override
    public String toString() {
        return "Order{" +

                ", destination='" + destination + '\'' +
                ", cargoWeight=" + cargoWeight +
                '}';
    }
}

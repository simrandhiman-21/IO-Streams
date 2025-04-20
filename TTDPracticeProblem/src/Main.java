class Ride {
    enum RideType { NORMAL, PREMIUM }

    RideType rideType;
    double distance; // in km
    int time;        // in minutes

    public Ride(RideType rideType, double distance, int time) {
        this.rideType = rideType;
        this.distance = distance;
        this.time = time;
    }
}

class InvoiceSummary {
    int totalRides;
    double totalFare;
    double averageFare;

    public InvoiceSummary(int totalRides, double totalFare) {
        this.totalRides = totalRides;
        this.totalFare = totalFare;
        this.averageFare = totalFare / totalRides;
    }

    public String toString() {
        return "Total Rides: " + totalRides +
                ", Total Fare: Rs. " + totalFare +
                ", Average Fare: Rs. " + averageFare;
    }
}

class CabInvoiceGenerator {
    // Fare rates
    private static final double NORMAL_COST_PER_KM = 10;
    private static final int NORMAL_COST_PER_MIN = 1;
    private static final double NORMAL_MIN_FARE = 5;

    private static final double PREMIUM_COST_PER_KM = 15;
    private static final int PREMIUM_COST_PER_MIN = 2;
    private static final double PREMIUM_MIN_FARE = 20;

    public double calculateFare(Ride ride) {
        double fare;
        if (ride.rideType == Ride.RideType.PREMIUM) {
            fare = ride.distance * PREMIUM_COST_PER_KM + ride.time * PREMIUM_COST_PER_MIN;
            return Math.max(fare, PREMIUM_MIN_FARE);
        } else {
            fare = ride.distance * NORMAL_COST_PER_KM + ride.time * NORMAL_COST_PER_MIN;
            return Math.max(fare, NORMAL_MIN_FARE);
        }
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
}

public class Main {
    public static void main(String[] args) {
        CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();

        Ride[] rides = {
                new Ride(Ride.RideType.NORMAL, 2.0, 5),
                new Ride(Ride.RideType.PREMIUM, 0.1, 1),
                new Ride(Ride.RideType.NORMAL, 5.0, 10)
        };

        InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
        System.out.println(summary);
    }
}

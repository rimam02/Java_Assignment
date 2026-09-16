package cab;
import java.util.Scanner;
import cab.booking.CabBooking;
import cab.booking.CabBooking.CabType;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Smart Cab Booking System =====");
        System.out.println();

  
        System.out.print("Enter Passenger ID: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Passenger Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Cab Type (MINI / SEDAN / SUV): ");
        String type = sc.nextLine().toUpperCase();

        System.out.print("Enter Base Fare: ₹");
        double fare = sc.nextDouble();

        sc.nextLine();

        System.out.print("Enter Pickup Location: ");
        String location = sc.nextLine();

    
        CabType cabType = CabType.valueOf(type);


        Integer passengerId = id;
        Double baseFare = fare;

        CabBooking booking = new CabBooking(
                passengerId,
                name,
                cabType,
                baseFare
        );

        System.out.println();


        System.out.println(booking.getBookingSummary());


        CabBooking.PickupLocation pickup =
                booking.new PickupLocation(location);

        pickup.displayLocation();

        System.out.println();

       
        booking.confirmBooking();

        sc.close();
    }
}

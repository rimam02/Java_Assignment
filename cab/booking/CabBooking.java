package cab.booking;

public class CabBooking {

    public enum CabType {
        MINI,
        SEDAN,
        SUV
    }

    private final Double bookingFee = 50.0;

    private Integer passengerId;
    private String passengerName;
    private CabType cabType;
    private Double baseFare;

    public CabBooking(Integer passengerId, String passengerName,
                      CabType cabType, Double baseFare) {

        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.cabType = cabType;
        this.baseFare = baseFare;
    }

    public class PickupLocation {

        private String location;

        public PickupLocation(String location) {
            this.location = location;
        }

        public void displayLocation() {
            System.out.println("Pickup Location: " + location);
        }
    }

    public String getBookingSummary() {
        
        double fare = baseFare;
        double fee = bookingFee;

        double finalFare = fare + fee;


        StringBuilder summary = new StringBuilder();

        summary.append("===== Smart Cab Booking System =====\n\n");
        summary.append("Passenger Name: ").append(passengerName).append("\n");
        summary.append("Cab Type: ").append(cabType).append("\n");
        summary.append("Base Fare: ₹").append(String.format("%.0f", fare)).append("\n");
        summary.append("Booking Fee: ₹").append(String.format("%.0f", fee)).append("\n");
        summary.append("Final Fare: ₹").append(String.format("%.0f", finalFare)).append("\n");
        summary.append("Passenger ID: ").append(passengerId).append("\n");

        return summary.toString();
    }

    public void confirmBooking() {

        BookingConfirmation confirmation = new BookingConfirmation() {

            @Override
            public void showMessage() {
                System.out.println("Booking confirmed successfully.");
            }
        };

        confirmation.showMessage();
    }
 
    interface BookingConfirmation {
        void showMessage();
    }
}

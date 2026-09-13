import java.util.Scanner;

class Room {
    private int roomNumber;
    private String roomType;
    private String customerName;
    private int numberOfDays;
    private double pricePerDay;
    private boolean isBooked;

    private static int totalBookings = 0;

    public Room(int roomNumber, String roomType, double pricePerDay) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerDay = pricePerDay;
        this.customerName = "";
        this.numberOfDays = 0;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public static int getTotalBookings() {
        return totalBookings;
    }

    public void bookRoom(String customerName, int numberOfDays) {
        if (this.isBooked) {
            System.out.println("\nRoom " + this.roomNumber + " is already booked!");
            return;
        }
        this.customerName = customerName;
        this.numberOfDays = numberOfDays;
        this.isBooked = true;
        totalBookings++;

        System.out.println("\n===== Booking Confirmation =====\n");
        System.out.println("Room Number   : " + this.roomNumber);
        System.out.println("Room Type     : " + this.roomType);
        System.out.println("Customer Name : " + this.customerName);
        System.out.println("Number of Days: " + this.numberOfDays);
        System.out.println("Price Per Day : ₹" + (int) this.pricePerDay);
        System.out.println("\nTotal Bill    : ₹" + (int) calculateBill());
        System.out.println("\nRoom booked successfully.");
    }

    public void displayRoomDetails() {
        System.out.println("\n===== Room Details =====");
        System.out.println("Room Number   : " + this.roomNumber);
        System.out.println("Room Type     : " + this.roomType);
        System.out.println("Price Per Day : ₹" + (int) this.pricePerDay);
        System.out.println("Status        : " + (this.isBooked ? "Booked" : "Available"));
        if (this.isBooked) {
            System.out.println("Customer Name : " + this.customerName);
            System.out.println("Number of Days: " + this.numberOfDays);
        }
    }

    public double calculateBill() {
        return this.numberOfDays * this.pricePerDay;
    }

    public void cancelBooking() {
        if (!this.isBooked) {
            System.out.println("\nRoom " + this.roomNumber + " is not currently booked.");
            return;
        }
        this.isBooked = false;
        this.customerName = "";
        this.numberOfDays = 0;
        totalBookings--;
        System.out.println("\nBooking for Room " + this.roomNumber + " cancelled successfully.");
    }
}

public class HotelRoomBookingSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Room[] rooms = {
            new Room(101, "Standard", 2000),
            new Room(102, "Standard", 2000),
            new Room(201, "Deluxe", 3500),
            new Room(202, "Deluxe", 3500),
            new Room(301, "Premium", 5000)
        };

        while (true) {
            System.out.println("\n===== Hotel Room Booking System =====");
            System.out.println("1. Book Room");
            System.out.println("2. Display Room Details");
            System.out.println("3. Calculate Bill");
            System.out.println("4. Check Room Status");
            System.out.println("5. Cancel Booking");
            System.out.println("6. Display Total Bookings");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Room Number (101, 102, 201, 202, 301): ");
                    int roomNum = scanner.nextInt();
                    Room roomToBook = findRoom(rooms, roomNum);

                    if (roomToBook != null) {
                        scanner.nextLine();
                        System.out.print("Enter Customer Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Number of Days: ");
                        int days = scanner.nextInt();
                        roomToBook.bookRoom(name, days);
                    } else {
                        System.out.println("Invalid Room Number!");
                    }
                    break;

                case 2:
                    System.out.print("Enter Room Number: ");
                    int rNumDisplay = scanner.nextInt();
                    Room roomDisplay = findRoom(rooms, rNumDisplay);
                    if (roomDisplay != null) {
                        roomDisplay.displayRoomDetails();
                    } else {
                        System.out.println("Invalid Room Number!");
                    }
                    break;

                case 3:
                    System.out.print("Enter Room Number: ");
                    int rNumBill = scanner.nextInt();
                    Room roomBill = findRoom(rooms, rNumBill);
                    if (roomBill != null) {
                        if (roomBill.isBooked()) {
                            System.out.println("Total Bill for Room " + rNumBill + ": ₹" + (int) roomBill.calculateBill());
                        } else {
                            System.out.println("Room is not currently booked.");
                        }
                    } else {
                        System.out.println("Invalid Room Number!");
                    }
                    break;

                case 4:
                    System.out.println("\n===== Room Status =====");
                    for (Room r : rooms) {
                        System.out.println("Room " + r.getRoomNumber() + " (" + r.getRoomType() + "): " + (r.isBooked() ? "Booked" : "Available"));
                    }
                    break;

                case 5:
                    System.out.print("Enter Room Number to Cancel: ");
                    int rNumCancel = scanner.nextInt();
                    Room roomCancel = findRoom(rooms, rNumCancel);
                    if (roomCancel != null) {
                        roomCancel.cancelBooking();
                    } else {
                        System.out.println("Invalid Room Number!");
                    }
                    break;

                case 6:
                    System.out.println("\nTotal Bookings across all rooms: " + Room.getTotalBookings());
                    break;

                case 7:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static Room findRoom(Room[] rooms, int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) {
                return r;
            }
        }
        return null;
    }
}
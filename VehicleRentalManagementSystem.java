import java.util.Scanner;

class Vehicle {
    private String vehicleNumber;
    private String vehicleModel;
    private String customerName;
    private int rentalDays;

    public Vehicle(String vehicleNumber, String vehicleModel, String customerName, int rentalDays) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleModel = vehicleModel;
        this.customerName = customerName;
        this.rentalDays = rentalDays;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRentalDays() {
        return rentalDays;
    }
}

class Car extends Vehicle {
    private int numberOfSeats;
    private static final double RATE_PER_DAY = 1500;

    public Car(String vehicleNumber, String vehicleModel, String customerName, int rentalDays, int numberOfSeats) {
        super(vehicleNumber, vehicleModel, customerName, rentalDays);
        this.numberOfSeats = numberOfSeats;
    }

    public double calculateRentalCharges() {
        return getRentalDays() * RATE_PER_DAY;
    }

    public void displayReceipt() {
        System.out.println("\n===== Vehicle Rental Receipt =====\n");
        System.out.println("Vehicle Type  : Car");
        System.out.println("Vehicle Number: " + getVehicleNumber());
        System.out.println("Model         : " + getVehicleModel());
        System.out.println("Customer Name : " + getCustomerName());
        System.out.println("Seats         : " + numberOfSeats);
        System.out.println("Rental Days   : " + getRentalDays());
        System.out.println("Rate Per Day  : ₹" + (int) RATE_PER_DAY);
        System.out.println("\nTotal Amount  : ₹" + (int) calculateRentalCharges());
        System.out.println("\nVehicle rented successfully.");
    }
}

class Bike extends Vehicle {
    private int engineCapacity;
    private static final double RATE_PER_DAY = 700;

    public Bike(String vehicleNumber, String vehicleModel, String customerName, int rentalDays, int engineCapacity) {
        super(vehicleNumber, vehicleModel, customerName, rentalDays);
        this.engineCapacity = engineCapacity;
    }

    public double calculateRentalCharges() {
        return getRentalDays() * RATE_PER_DAY;
    }

    public void displayReceipt() {
        System.out.println("\n===== Vehicle Rental Receipt =====\n");
        System.out.println("Vehicle Type  : Bike");
        System.out.println("Vehicle Number: " + getVehicleNumber());
        System.out.println("Model         : " + getVehicleModel());
        System.out.println("Customer Name : " + getCustomerName());
        System.out.println("Engine (cc)   : " + engineCapacity);
        System.out.println("Rental Days   : " + getRentalDays());
        System.out.println("Rate Per Day  : ₹" + (int) RATE_PER_DAY);
        System.out.println("\nTotal Amount  : ₹" + (int) calculateRentalCharges());
        System.out.println("\nVehicle rented successfully.");
    }
}

class Scooter extends Vehicle {
    private int storageCapacity;
    private static final double RATE_PER_DAY = 500;

    public Scooter(String vehicleNumber, String vehicleModel, String customerName, int rentalDays, int storageCapacity) {
        super(vehicleNumber, vehicleModel, customerName, rentalDays);
        this.storageCapacity = storageCapacity;
    }

    public double calculateRentalCharges() {
        return getRentalDays() * RATE_PER_DAY;
    }

    public void displayReceipt() {
        System.out.println("\n===== Vehicle Rental Receipt =====\n");
        System.out.println("Vehicle Type  : Scooter");
        System.out.println("Vehicle Number: " + getVehicleNumber());
        System.out.println("Model         : " + getVehicleModel());
        System.out.println("Customer Name : " + getCustomerName());
        System.out.println("Storage (L)   : " + storageCapacity);
        System.out.println("Rental Days   : " + getRentalDays());
        System.out.println("Rate Per Day  : ₹" + (int) RATE_PER_DAY);
        System.out.println("\nTotal Amount  : ₹" + (int) calculateRentalCharges());
        System.out.println("\nVehicle rented successfully.");
    }
}

class ElectricCar extends Car {
    private int batteryCapacity;
    private static final double RATE_PER_DAY = 2000;

    public ElectricCar(String vehicleNumber, String vehicleModel, String customerName, int rentalDays, int numberOfSeats, int batteryCapacity) {
        super(vehicleNumber, vehicleModel, customerName, rentalDays, numberOfSeats);
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public double calculateRentalCharges() {
        return getRentalDays() * RATE_PER_DAY;
    }

    @Override
    public void displayReceipt() {
        System.out.println("\n===== Vehicle Rental Receipt =====\n");
        System.out.println("Vehicle Type  : Electric Car");
        System.out.println("Vehicle Number: " + getVehicleNumber());
        System.out.println("Model         : " + getVehicleModel());
        System.out.println("Customer Name : " + getCustomerName());
        System.out.println("Battery (kWh) : " + batteryCapacity);
        System.out.println("Rental Days   : " + getRentalDays());
        System.out.println("Rate Per Day  : ₹" + (int) RATE_PER_DAY);
        System.out.println("\nTotal Amount  : ₹" + (int) calculateRentalCharges());
        System.out.println("\nVehicle rented successfully.");
    }
}

public class VehicleRentalManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Vehicle lastRentedVehicle = null;

        while (true) {
            System.out.println("\n===== Vehicle Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Rent a Bike");
            System.out.println("3. Rent a Scooter");
            System.out.println("4. Rent an Electric Car");
            System.out.println("5. Display Rental Details");
            System.out.println("6. Calculate Rental Charges");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice >= 1 && choice <= 4) {
                System.out.print("Enter Vehicle Number: ");
                String vNum = scanner.nextLine();
                System.out.print("Enter Model: ");
                String model = scanner.nextLine();
                System.out.print("Enter Customer Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Rental Days: ");
                int days = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Number of Seats: ");
                        int seats = scanner.nextInt();
                        Car car = new Car(vNum, model, name, days, seats);
                        lastRentedVehicle = car;
                        car.displayReceipt();
                        break;
                    case 2:
                        System.out.print("Enter Engine Capacity (cc): ");
                        int cc = scanner.nextInt();
                        Bike bike = new Bike(vNum, model, name, days, cc);
                        lastRentedVehicle = bike;
                        bike.displayReceipt();
                        break;
                    case 3:
                        System.out.print("Enter Storage Capacity (L): ");
                        int storage = scanner.nextInt();
                        Scooter scooter = new Scooter(vNum, model, name, days, storage);
                        lastRentedVehicle = scooter;
                        scooter.displayReceipt();
                        break;
                    case 4:
                        System.out.print("Enter Number of Seats: ");
                        int eSeats = scanner.nextInt();
                        System.out.print("Enter Battery Capacity (kWh): ");
                        int battery = scanner.nextInt();
                        ElectricCar eCar = new ElectricCar(vNum, model, name, days, eSeats, battery);
                        lastRentedVehicle = eCar;
                        eCar.displayReceipt();
                        break;
                }
            } else if (choice == 5 || choice == 6) {
                if (lastRentedVehicle == null) {
                    System.out.println("\nNo active vehicle rental found!");
                } else {
                    if (lastRentedVehicle instanceof Car) {
                        ((Car) lastRentedVehicle).displayReceipt();
                    } else if (lastRentedVehicle instanceof Bike) {
                        ((Bike) lastRentedVehicle).displayReceipt();
                    } else if (lastRentedVehicle instanceof Scooter) {
                        ((Scooter) lastRentedVehicle).displayReceipt();
                    }
                }
            } else if (choice == 7) {
                System.out.println("Exiting system. Goodbye!");
                scanner.close();
                return;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }
}
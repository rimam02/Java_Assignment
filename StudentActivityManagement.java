import java.util.Scanner;

public class StudentActivityManagement {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        double attendance;
        int marks;


        while (true) {

            System.out.print("Enter attendance percentage (0-100): ");

            if (scanner.hasNextDouble()) {

                attendance = scanner.nextDouble();

                if (attendance >= 0 && attendance <= 100) {
                    break;
                } else {
                    System.out.println(
                        "Invalid attendance! Please enter a value between 0 and 100."
                    );
                }

            } else {

                System.out.println(
                    "Invalid input! Please enter a numeric attendance percentage."
                );

                scanner.next();
            }
        }

        while (true) {

            System.out.print("Enter marks (0-100): ");

            if (scanner.hasNextInt()) {

                marks = scanner.nextInt();

                if (marks >= 0 && marks <= 100) {
                    break;
                } else {
                    System.out.println(
                        "Invalid marks! Please enter marks between 0 and 100."
                    );
                }

            } else {

                System.out.println(
                    "Invalid input! Please enter numeric marks."
                );

                scanner.next();
            }
        }


        int choice;

        while (true) {

            System.out.println();
            System.out.println("===== Student Activity Management System =====");
            System.out.println("1. Check Attendance Eligibility");
            System.out.println("2. View Performance Category");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {

                System.out.println(
                    "Invalid menu choice! Please enter 1, 2, or 3."
                );

                scanner.next();

                continue;
            }

            choice = scanner.nextInt();

            switch (choice) {

  
                case 1:

                    if (attendance >= 75) {

                        System.out.println(
                            "Attendance: " + attendance + "%"
                        );

                        System.out.println(
                            "The student is eligible to appear for the examination."
                        );

                    } else {

                        System.out.println(
                            "Attendance: " + attendance + "%"
                        );

                        System.out.println(
                            "The student is not eligible to appear for the examination."
                        );
                    }

                    break;

                case 2:

                    System.out.println("Marks: " + marks);
 
                    if (marks >= 60) {

                        if (marks >= 90) {

                            System.out.println(
                                "Performance Category: Excellent"
                            );

                        } else if (marks >= 70) {

                            System.out.println(
                                "Performance Category: Good"
                            );

                        } else {

                            System.out.println(
                                "Performance Category: Average"
                            );
                        }

                    } else {

                        System.out.println(
                            "Performance Category: Needs Improvement"
                        );
                    }

                    break;


                case 3:

                    System.out.println(
                        "Thank you for using the Student Activity Management System."
                    );

                    System.out.println("Program terminated.");

                    break;

        
                default:

                    System.out.println(
                        "Invalid choice! Please select 1, 2, or 3."
                    );

          
                    continue;
            }


            if (choice == 3) {
                break;
            }
        }

        scanner.close();
    }
}
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentCourseEnrollmentManager {

    static ArrayList<String> enrolledStudents = new ArrayList<>();

    static LinkedList<String> waitingQueue = new LinkedList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Student Course Enrollment Manager =====");
            System.out.println("1. Add Student to Enrollment List");
            System.out.println("2. Display Enrolled Students");
            System.out.println("3. Add Student to Waiting Queue");
            System.out.println("4. Promote Student from Queue");
            System.out.println("5. Search Student in Enrollment List");
            System.out.println("6. Remove Student from Enrollment List");
            System.out.println("7. Display Waiting Queue");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
        
                    System.out.print("Enter student name to enroll: ");
                    String studentName = scanner.nextLine();

                    enrolledStudents.add(studentName);

                    System.out.println("Student enrolled successfully.");
                    System.out.println("Total enrolled students: "
                            + enrolledStudents.size());
                    break;

                case 2:
       
                    System.out.println("\n===== Enrolled Students =====");

                    if (enrolledStudents.isEmpty()) {
                        System.out.println("No students are currently enrolled.");
                    } else {
                        int number = 1;

                        for (String student : enrolledStudents) {
                            System.out.println(number + ". " + student);
                            number++;
                        }

                        System.out.println("Total enrolled students: "
                                + enrolledStudents.size());
                    }
                    break;

                case 3:
     
                    System.out.print("Enter student name for waiting queue: ");
                    String waitingStudent = scanner.nextLine();

                    waitingQueue.addLast(waitingStudent);

                    System.out.println("Student added to waiting queue.");
                    System.out.println("Total waiting students: "
                            + waitingQueue.size());
                    break;

                case 4:
            
                    try {
                        String promotedStudent = waitingQueue.removeFirst();

                        enrolledStudents.add(promotedStudent);

                        System.out.println(
                                promotedStudent
                                        + " moved to Enrollment List."
                        );

                        System.out.println("Total enrolled students: "
                                + enrolledStudents.size());

                        System.out.println("Students still waiting: "
                                + waitingQueue.size());

                    } catch (NoSuchElementException e) {
                        System.out.println(
                                "Cannot promote student. The waiting queue is empty."
                        );
                    }
                    break;

                case 5:
           
                    System.out.print("Enter student name to search: ");
                    String searchName = scanner.nextLine();

                    if (enrolledStudents.contains(searchName)) {

                        int index = enrolledStudents.indexOf(searchName);

                        System.out.println(
                                "Student found at index: " + index
                        );

                    } else {
                        System.out.println(
                                "Student not found in enrollment list."
                        );
                    }
                    break;

                case 6:

                    System.out.print("Enter student name to remove: ");
                    String removeName = scanner.nextLine();

                    try {
                        int index = enrolledStudents.indexOf(removeName);

                        if (index == -1) {
                            throw new IndexOutOfBoundsException();
                        }

                        enrolledStudents.remove(index);

                        System.out.println(
                                "Student removed successfully."
                        );

                        System.out.println("Total enrolled students: "
                                + enrolledStudents.size());

                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(
                                "Student not found in enrollment list."
                        );
                    }
                    break;

                case 7:
   
                    System.out.println("\n===== Waiting Queue =====");

                    if (waitingQueue.isEmpty()) {
                        System.out.println("No students are waiting.");
                    } else {
                        int number = 1;

                        for (String student : waitingQueue) {
                            System.out.println(number + ". " + student);
                            number++;
                        }

                        System.out.println("Total waiting students: "
                                + waitingQueue.size());
                    }
                    break;

                case 8:
                    System.out.println(
                            "Thank you for using Student Course Enrollment Manager."
                    );
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please enter a number between 1 and 8."
                    );
            }

        } while (choice != 8);

        scanner.close();
    }
}
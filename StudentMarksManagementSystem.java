import java.util.Scanner;

public class StudentMarksManagementSystem {

    public static void main(String[] args) {
        int[][] marks = {
            {78, 82, 75},
            {90, 85, 88},
            {65, 72, 70},
            {88, 91, 85},
            {55, 60, 58}
        };

        String[] students = {"Student 1", "Student 2", "Student 3", "Student 4", "Student 5"};
        String[] subjects = {"Java", "Python", "DBMS"};

        int[] totals = calculateTotals(marks);
        double[] averages = calculateAverages(totals, marks[0].length);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Student Marks Management System =====");
            System.out.println("1. Display All Marks");
            System.out.println("2. Calculate Student Total & Average");
            System.out.println("3. Find Highest Scorer & Subject-Wise Highest");
            System.out.println("4. Search Marks");
            System.out.println("5. Sort Student Totals");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            if (!scanner.hasNextInt()) {
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayMarks(marks, students, subjects);
                    break;
                case 2:
                    displayTotalsAndAverages(students, totals, averages);
                    break;
                case 3:
                    findHighestScorer(students, totals);
                    findSubjectWiseHighest(marks, subjects);
                    break;
                case 4:
                    System.out.print("Enter mark to search: ");
                    if (scanner.hasNextInt()) {
                        int target = scanner.nextInt();
                        searchMark(marks, target);
                    }
                    break;
                case 5:
                    sortTotals(students, totals);
                    break;
                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static int[] calculateTotals(int[][] marks) {
        int[] totals = new int[marks.length];
        for (int i = 0; i < marks.length; i++) {
            for (int j = 0; j < marks[i].length; j++) {
                totals[i] += marks[i][j];
            }
        }
        return totals;
    }

    public static double[] calculateAverages(int[] totals, int subjectCount) {
        double[] averages = new double[totals.length];
        for (int i = 0; i < totals.length; i++) {
            averages[i] = (double) totals[i] / subjectCount;
        }
        return averages;
    }

    public static void displayMarks(int[][] marks, String[] students, String[] subjects) {
        System.out.println("\nStudent\t\tJava\tPython\tDBMS");
        for (int i = 0; i < marks.length; i++) {
            System.out.print(students[i] + "\t");
            for (int j = 0; j < marks[i].length; j++) {
                System.out.print(marks[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void displayTotalsAndAverages(String[] students, int[] totals, double[] averages) {
        System.out.println();
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i] + " Total   : " + totals[i]);
            System.out.printf("%s Average : %.2f\n\n", students[i], averages[i]);
        }
    }

    public static void findHighestScorer(String[] students, int[] totals) {
        int maxIndex = 0;
        for (int i = 1; i < totals.length; i++) {
            if (totals[i] > totals[maxIndex]) {
                maxIndex = i;
            }
        }
        System.out.println("\nHighest Scorer: " + students[maxIndex]);
    }

    public static void findSubjectWiseHighest(int[][] marks, String[] subjects) {
        System.out.println();
        for (int j = 0; j < subjects.length; j++) {
            int maxMark = marks[0][j];
            for (int i = 1; i < marks.length; i++) {
                if (marks[i][j] > maxMark) {
                    maxMark = marks[i][j];
                }
            }
            System.out.println("Highest " + subjects[j] + " Marks : " + maxMark);
        }
    }

    public static void searchMark(int[][] marks, int target) {
        boolean found = false;
        for (int i = 0; i < marks.length; i++) {
            for (int j = 0; j < marks[i].length; j++) {
                if (marks[i][j] == target) {
                    System.out.println("Mark " + target + " found for Student " + (i + 1) + " in Subject " + (j + 1));
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Mark " + target + " not found.");
        }
    }

    public static void sortTotals(String[] students, int[] totals) {
        String[] sortedStudents = students.clone();
        int[] sortedTotals = totals.clone();

        for (int i = 0; i < sortedTotals.length - 1; i++) {
            for (int j = 0; j < sortedTotals.length - i - 1; j++) {
                if (sortedTotals[j] < sortedTotals[j + 1]) {
                    int tempTotal = sortedTotals[j];
                    sortedTotals[j] = sortedTotals[j + 1];
                    sortedTotals[j + 1] = tempTotal;

                    String tempStudent = sortedStudents[j];
                    sortedStudents[j] = sortedStudents[j + 1];
                    sortedStudents[j + 1] = tempStudent;
                }
            }
        }

        System.out.println("\n===== Sorted Totals (Descending) =====");
        for (int i = 0; i < sortedTotals.length; i++) {
            System.out.println((i + 1) + ". " + sortedStudents[i] + " - " + sortedTotals[i]);
        }
    }
}
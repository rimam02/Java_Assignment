
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class StudentRecordFileManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File dir = new File("StudentRecord");
        File file = new File(dir, "student.txt");
        int choice = 0;

        do {
            System.out.println("\n===== Student Record File Manager =====");
            System.out.println("1. Create Records Directory");
            System.out.println("2. Create Record File");
            System.out.println("3. Write Student Record");
            System.out.println("4. Display File Information");
            System.out.println("5. Read File Content");
            System.out.println("6. Append New Record");
            System.out.println("7. Delete Record File");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 8.");
                scanner.next();
                continue;
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createDirectory(dir);
                    break;
                case 2:
                    createRecordFile(dir, file);
                    break;
                case 3:
                    writeRecord(scanner, file);
                    break;
                case 4:
                    displayFileInfo(file);
                    break;
                case 5:
                    readFileContent(file);
                    break;
                case 6:
                    appendRecord(scanner, file);
                    break;
                case 7:
                    deleteRecordFile(file);
                    break;
                case 8:
                    System.out.println("Exiting application...");
                    break;
                default:
                    System.out.println("Invalid choice! Select an option from 1 to 8.");
            }
        } while (choice != 8);

        scanner.close();
    }

    private static void createDirectory(File dir) {
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Directory created: " + dir.getName());
            } else {
                System.out.println("Failed to create directory.");
            }
        } else {
            System.out.println("Directory already exists: " + dir.getName());
        }
    }

    private static void createRecordFile(File dir, File file) {
        try {
            if (!dir.exists()) {
                System.out.println("Directory does not exist. Creating directory first...");
                dir.mkdir();
            }
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getPath());
                } else {
                    System.out.println("Failed to create file.");
                }
            } else {
                System.out.println("File already exists: " + file.getPath());
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    private static void writeRecord(Scanner scanner, File file) {
        System.out.print("Enter Student Record: ");
        String record = scanner.nextLine();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(record.getBytes());
            System.out.println("Record written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void displayFileInfo(File file) {
        if (file.exists()) {
            System.out.println("===== File Information =====");
            System.out.println("Name          : " + file.getName());
            System.out.println("Path          : " + file.getPath());
            System.out.println("Absolute Path : " + file.getAbsolutePath());
            System.out.println("Size          : " + file.length() + " bytes");
            System.out.println("Is File       : " + file.isFile());
            System.out.println("Is Directory  : " + file.isDirectory());
        } else {
            System.out.println("File does not exist.");
        }
    }

    private static void readFileContent(File file) {
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        System.out.println("===== File Content =====");
        try (FileInputStream fis = new FileInputStream(file)) {
            int byteData;
            while ((byteData = fis.read()) != -1) {
                System.out.print((char) byteData);
            }
            System.out.println();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void appendRecord(Scanner scanner, File file) {
        System.out.print("Enter Student Record to Append: ");
        String record = "\n" + scanner.nextLine();
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write(record.getBytes());
            System.out.println("Record appended successfully.");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    private static void deleteRecordFile(File file) {
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}

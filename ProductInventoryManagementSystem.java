import java.util.HashSet;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.NavigableMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ProductInventoryManagementSystem {

    static HashSet<String> categories = new HashSet<>();

    static TreeSet<Integer> productIds = new TreeSet<>();

    static TreeMap<Integer, String> productCatalog = new TreeMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Product Inventory Management System =====");
            System.out.println("1. Add Product Category");
            System.out.println("2. Add Product ID");
            System.out.println("3. Add Product to Catalog");
            System.out.println("4. Display All Products");
            System.out.println("5. Find Nearest Product ID");
            System.out.println("6. Display Products in ID Range");
            System.out.println("7. Remove Product");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
            
                    System.out.print("Enter category name: ");
                    String category = scanner.nextLine();

                    if (categories.add(category)) {
                        System.out.println("Category added: " + category);
                    } else {
                        System.out.println(
                                "Category already exists. Duplicate ignored."
                        );
                    }
                    break;

                case 2:
        
                    System.out.print("Enter product ID: ");
                    int productId = scanner.nextInt();
                    scanner.nextLine();

                    if (productIds.add(productId)) {
                        System.out.println("Product ID added successfully.");
                    } else {
                        System.out.println(
                                "Product ID already exists. Duplicate ignored."
                        );
                    }

                    System.out.println("\n===== Product IDs (Sorted) =====");

                    Iterator<Integer> iterator = productIds.iterator();

                    if (!iterator.hasNext()) {
                        System.out.println("No product IDs available.");
                    } else {
                        while (iterator.hasNext()) {
                            System.out.println(iterator.next());
                        }
                    }
                    break;

                case 3:
     
                    System.out.print("Enter product ID: ");
                    int catalogId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print(
                            "Enter product name and price: "
                    );
                    String details = scanner.nextLine();

                    productCatalog.put(catalogId, details);

                    productIds.add(catalogId);

                    System.out.println("Product added to catalog.");
                    break;

                case 4:
         
                    System.out.println(
                            "\n===== All Products (Sorted by ID) ====="
                    );

                    if (productCatalog.isEmpty()) {
                        System.out.println("No products in catalog.");
                    } else {
                        for (Map.Entry<Integer, String> entry
                                : productCatalog.entrySet()) {

                            System.out.println(
                                    entry.getKey()
                                            + " -> "
                                            + entry.getValue()
                            );
                        }
                    }
                    break;

                case 5:
       
                    System.out.print("Enter ID to search nearest: ");
                    int searchId = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        if (productCatalog.isEmpty()) {
                            throw new NoSuchElementException();
                        }

                        NavigableMap<Integer, String> navigableCatalog =
                                productCatalog;

                        Integer floorId =
                                navigableCatalog.floorKey(searchId);

                        Integer ceilingId =
                                navigableCatalog.ceilingKey(searchId);

                        if (floorId != null) {
                            System.out.println(
                                    "Floor ID : " + floorId
                            );
                        } else {
                            System.out.println(
                                    "No product ID at or below " + searchId
                            );
                        }

                        if (ceilingId != null) {
                            System.out.println(
                                    "Ceiling ID: " + ceilingId
                            );
                        } else {
                            System.out.println(
                                    "No product ID at or above " + searchId
                            );
                        }

                    } catch (NoSuchElementException e) {
                        System.out.println(
                                "Cannot search. The product catalog is empty."
                        );
                    }
                    break;

                case 6:
     
                    System.out.print("Enter starting ID: ");
                    int fromId = scanner.nextInt();

                    System.out.print("Enter ending ID: ");
                    int toId = scanner.nextInt();
                    scanner.nextLine();

                    try {
                        if (productCatalog.isEmpty()) {
                            throw new NoSuchElementException();
                        }

                        NavigableMap<Integer, String> rangeMap =
                                productCatalog.subMap(
                                        fromId,
                                        true,
                                        toId,
                                        true
                                );

                        System.out.println(
                                "\n===== Products in Range ====="
                        );

                        if (rangeMap.isEmpty()) {
                            System.out.println(
                                    "No products found in the specified range."
                            );
                        } else {
                            for (Map.Entry<Integer, String> entry
                                    : rangeMap.entrySet()) {

                                System.out.println(
                                        entry.getKey()
                                                + " -> "
                                                + entry.getValue()
                                );
                            }
                        }

                    } catch (NoSuchElementException e) {
                        System.out.println(
                                "Cannot perform range search. "
                                        + "The product catalog is empty."
                        );
                    } catch (IllegalArgumentException e) {
                        System.out.println(
                                "Invalid range. Starting ID must not be "
                                        + "greater than ending ID."
                        );
                    }
                    break;

                case 7:
               
                    System.out.print("Enter product ID to remove: ");
                    int removeId = scanner.nextInt();
                    scanner.nextLine();

                    if (productCatalog.containsKey(removeId)) {

                        productCatalog.remove(removeId);
                        productIds.remove(removeId);

                        System.out.println(
                                "Product removed successfully."
                        );

                    } else {
                        System.out.println(
                                "Product ID not found in catalog."
                        );
                    }
                    break;

                case 8:
                    System.out.println(
                            "Thank you for using Product Inventory "
                                    + "Management System."
                    );
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Please enter a number "
                                    + "between 1 and 8."
                    );
            }

        } while (choice != 8);

        scanner.close();
    }
}
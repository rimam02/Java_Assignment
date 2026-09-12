import java.util.Scanner;

class InvalidCouponException extends Exception {

    public InvalidCouponException(String message) {
        super(message);
    }
}

public class CouponValidator {

    static double calculateDiscount(String couponCode, double orderAmount)
            throws InvalidCouponException {

        double discountRate;

        if (couponCode.equalsIgnoreCase("SAVE10")) {

            if (orderAmount < 1000) {
                throw new InvalidCouponException(
                        "SAVE10 requires a minimum order of ₹1,000."
                );
            }

            discountRate = 0.10;

        } else if (couponCode.equalsIgnoreCase("SAVE20")) {

            if (orderAmount < 2000) {
                throw new InvalidCouponException(
                        "SAVE20 requires a minimum order of ₹2,000."
                );
            }

            discountRate = 0.20;

        } else if (couponCode.equalsIgnoreCase("SAVE30")) {

            if (orderAmount < 3000) {
                throw new InvalidCouponException(
                        "SAVE30 requires a minimum order of ₹3,000."
                );
            }

            discountRate = 0.30;

        } else {

            throw new InvalidCouponException(
                    "Invalid coupon code."
            );
        }

        return orderAmount * discountRate;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== E-Commerce Coupon Validator =====");

        System.out.print("Enter order amount: ₹");
        double orderAmount = scanner.nextDouble();

        System.out.print("Enter coupon code: ");
        String couponCode = scanner.next();

        try {

            double discount = calculateDiscount(couponCode, orderAmount);

            double finalAmount = orderAmount - discount;

            System.out.println("\nCoupon validation completed.");
            System.out.println("Order Amount : ₹" + orderAmount);
            System.out.println("Coupon Code  : " + couponCode);
            System.out.println("Discount     : ₹" + discount);
            System.out.println("Final Payable Amount : ₹" + finalAmount);

        } catch (InvalidCouponException e) {

            System.out.println("\nCoupon Error: " + e.getMessage());

        } finally {

            System.out.println("\nCoupon validation is completed.");

        }

        scanner.close();
    }
}

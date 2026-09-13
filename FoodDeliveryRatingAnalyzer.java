public class FoodDeliveryRatingAnalyzer {

    static int calculateTotal(int[] ratings) {

        int total = 0;

        for (int rating : ratings) {
            total += rating;
        }

        return total;
    }

    static int[] calculateTotal(int[][] ratings) {

        int[] totals = new int[ratings.length];

        for (int i = 0; i < ratings.length; i++) {
            totals[i] = calculateTotal(ratings[i]);
        }

        return totals;
    }

    static double calculateAverage(int[] ratings) {

        int total = calculateTotal(ratings);

        return (double) total / ratings.length;
    }

    static boolean linearSearch(int[][] ratings, int searchValue) {

        for (int i = 0; i < ratings.length; i++) {

            for (int j = 0; j < ratings[i].length; j++) {

                if (ratings[i][j] == searchValue) {
                    return true;
                }
            }
        }

        return false;
    }

    static void bubbleSort(int[] totals, String[] partners) {

        for (int i = 0; i < totals.length - 1; i++) {

            for (int j = 0; j < totals.length - 1 - i; j++) {

                if (totals[j] < totals[j + 1]) {

                    int tempTotal = totals[j];
                    totals[j] = totals[j + 1];
                    totals[j + 1] = tempTotal;

                    String tempPartner = partners[j];
                    partners[j] = partners[j + 1];
                    partners[j + 1] = tempPartner;
                }
            }
        }
    }

    public static void main(String[] args) {

        String[] partners = {
            "Partner 1",
            "Partner 2",
            "Partner 3",
            "Partner 4",
            "Partner 5"
        };

        int[][] ratings = {
            {4, 5, 4, 5},   
            {3, 4, 5, 4},  
            {5, 5, 5, 5},   
            {4, 3, 4, 3}, 
            {5, 4, 5, 4}   
        };

        System.out.println("===== Food Delivery Rating Analysis =====");
        System.out.println();

        int[] totalRatings = calculateTotal(ratings);

        for (int i = 0; i < ratings.length; i++) {

            double average = calculateAverage(ratings[i]);

            System.out.println(
                partners[i] + " Total   : " + totalRatings[i]
            );

            System.out.printf(
                "%s Average : %.2f%n",
                partners[i],
                average
            );

            System.out.println();
        }
        
        int searchValue = 5;

        System.out.println("===== Linear Search =====");
        System.out.println(
            "Searching for rating value: " + searchValue
        );

        boolean found = linearSearch(ratings, searchValue);

        if (found) {
            System.out.println(
                "Rating " + searchValue + " was found in the data."
            );
        } else {
            System.out.println(
                "Rating " + searchValue + " was not found in the data."
            );
        }

        System.out.println();

        int[] rankingTotals = totalRatings.clone();
        String[] rankingPartners = partners.clone();

        bubbleSort(rankingTotals, rankingPartners);

        System.out.println("===== Ranking =====");
        System.out.println();

        for (int i = 0; i < rankingPartners.length; i++) {

            System.out.println(
                (i + 1) + ". " + rankingPartners[i]
            );
        }

        System.out.println();

        System.out.println(
            "Highest Rated Partner: " + rankingPartners[0]
        );
    }
}
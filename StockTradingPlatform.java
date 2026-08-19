import java.util.*;

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] stocks = {"Apple", "Tesla"};
        double[] prices = {180, 250};
        int[] shares = {0, 0};

        double balance = 10000;
        int choice;

        do {
            System.out.println("\n--- STOCK TRADING PLATFORM ---");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                for (int i = 0; i < stocks.length; i++) {
                    System.out.println((i + 1) + ". " + stocks[i]
                            + " - Rs. " + prices[i]);
                }
            }

            else if (choice == 2 || choice == 3) {
                System.out.println("1. Apple - Rs. " + prices[0]);
                System.out.println("2. Tesla - Rs. " + prices[1]);

                System.out.print("Select stock: ");
                int stockChoice = sc.nextInt() - 1;

                if (stockChoice < 0 || stockChoice >= stocks.length) {
                    System.out.println("Invalid stock choice.");
                    continue;
                }

                System.out.print("Enter quantity: ");
                int quantity = sc.nextInt();

                if (quantity <= 0) {
                    System.out.println("Quantity must be positive.");
                    continue;
                }

                double amount = prices[stockChoice] * quantity;

                if (choice == 2) {
                    if (balance >= amount) {
                        balance -= amount;
                        shares[stockChoice] += quantity;
                        System.out.println("Stock bought successfully.");
                    } else {
                        System.out.println("Insufficient balance.");
                    }
                } else {
                    if (shares[stockChoice] >= quantity) {
                        shares[stockChoice] -= quantity;
                        balance += amount;
                        System.out.println("Stock sold successfully.");
                    } else {
                        System.out.println("You do not have enough shares.");
                    }
                }
            }

            else if (choice == 4) {
                double stockValue = 0;

                System.out.println("\n--- YOUR PORTFOLIO ---");
                for (int i = 0; i < stocks.length; i++) {
                    System.out.println(stocks[i] + " Shares: " + shares[i]);
                    stockValue += shares[i] * prices[i];
                }

                System.out.println("Cash Balance: Rs. " + balance);
                System.out.println("Stock Value: Rs. " + stockValue);
                System.out.println("Total Value: Rs. " + (balance + stockValue));
            }

        } while (choice != 5);

        System.out.println("Thank you!");
        sc.close();
    }
}

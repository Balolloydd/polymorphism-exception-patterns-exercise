package util;

public class UserInterface {
    private static final short TEXT_PADDING = 3;
    
    public static void displayMenu() {
        displayTitle("MENU OPERATIONS");
        System.out.println("1. Add Parcel");
        System.out.println("2. View All Parcels");
        System.out.println("3. Change Delivery Service");
        System.out.println("4. Mark as Delivered");
        System.out.println("5. Exit");
    }

    public static void displayTitle(String title) {
        System.out.println();
        System.out.println("=".repeat(TEXT_PADDING * 2 + title.length()));
        System.out.println(" ".repeat(TEXT_PADDING) + title + " ".repeat(TEXT_PADDING));
        System.out.println("=".repeat(TEXT_PADDING * 2 + title.length()));
        System.out.println();
    }

    public static void displayFeedback(String feedback) {
        System.out.println();
        System.out.println("-".repeat(TEXT_PADDING * 2 + feedback.length()));
        System.out.println(" ".repeat(TEXT_PADDING) + feedback + " ".repeat(TEXT_PADDING));
        System.out.println("-".repeat(TEXT_PADDING * 2 + feedback.length()));
        System.out.println();
    }
}

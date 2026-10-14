package util;

public class UserInterface {
    private static final short TEXT_PADDING = 3;
    private UserInterface() {} // Prevents Instantiation
    
    public static void displayMainMenu() {
        displayTitle("MENU OPERATIONS");
        System.out.println("1. Add Parcel");
        System.out.println("2. View All Parcels");
        System.out.println("3. Change Delivery Service");
        System.out.println("4. Mark as Delivered");
        System.out.println("5. Exit");
    }

    public static void displayParcelMenu() {
        displayTitle("PARCEL TYPES");
        System.out.println("1. Document");
        System.out.println("2. Box");
        System.out.println("3. Fragile");
    }

    public static void displayServiceMenu() {
        displayTitle("PARCEL SERVICES");
        System.out.println("1. Standard");
        System.out.println("2. Express");
        System.out.println("3. Same Day");
    }

    public static void displayTitle(String title) {
        Validation.validateNonEmptyStrings(title);
        
        System.out.println();
        System.out.println("=".repeat(TEXT_PADDING * 2 + title.length()));
        System.out.println(" ".repeat(TEXT_PADDING) + title + " ".repeat(TEXT_PADDING));
        System.out.println("=".repeat(TEXT_PADDING * 2 + title.length()));
        System.out.println();
    }

    public static void displayFeedback(String feedback) {
        Validation.validateNonEmptyStrings(feedback);

        String[] parts = feedback.split("\n");
        int longestElement = getLongestElementLength(parts);
        
        System.out.println();
        System.out.println("-".repeat(longestElement));
        
        for (String part : parts) {
            System.out.println(part + " ".repeat(longestElement - part.length()));
        }
        
        System.out.println("-".repeat(longestElement));
        System.out.println();
    }

    private static int getLongestElementLength(String[] parts) {
        int longestLength = -1;

        for (String part : parts) {
            longestLength = Math.max(longestLength, part.length());
        }

        return longestLength;
    }
}

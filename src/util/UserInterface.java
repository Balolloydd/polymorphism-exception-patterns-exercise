package util;
import java.util.Collection;
import model.Parcel;

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

    public static void displayParcelTable(Collection<Parcel> parcels) {
        String[] fields = {"TRACKING NUMBER", "TYPE", "RECEIVER", "CHARGEABLE WEIGHT", "SERVICE", "DELIVERY FEE", "STATUS"};
        
        int trackingNumberLength = fields[0].length();
        int typeLength = fields[1].length();
        int receiverLength = fields[2].length(); 
        int cWeightLength = fields[3].length();
        int serviceLength = fields[4].length();
        int deliveryFeeLength = fields[5].length();
        int statusLength = fields[6].length();
        
        for (Parcel parcel : parcels) {
            trackingNumberLength = Math.max(trackingNumberLength, parcel.getTrackingNumber().length());
            typeLength = Math.max(typeLength, parcel.getType().toString().length());
            receiverLength = Math.max(receiverLength, parcel.getReceiverName().length());
            cWeightLength = Math.max(cWeightLength, String.format("%.2f", parcel.getChargeableWeight()).length());
            serviceLength = Math.max(serviceLength, parcel.getDeliveryType().toString().length());
            deliveryFeeLength = Math.max(deliveryFeeLength, String.format("%.2f", parcel.getDeliveryFee()).length());
            statusLength = Math.max(statusLength, parcel.getDeliveryStatus().toString().length());
        }

        int totalLength = trackingNumberLength + typeLength + receiverLength + cWeightLength + serviceLength + deliveryFeeLength + statusLength;
        System.out.println("=".repeat(totalLength + 10));

        System.out.print(String.format("| %-" + trackingNumberLength + "s ", fields[0]));
        System.out.print(String.format("%-" + typeLength + "s ", fields[1]));
        System.out.print(String.format("%-" + receiverLength + "s ", fields[2]));
        System.out.print(String.format("%-" + cWeightLength + "s ", fields[3]));
        System.out.print(String.format("%-" + serviceLength + "s ", fields[4]));
        System.out.print(String.format("%-" + deliveryFeeLength + "s ", fields[5]));
        System.out.print(String.format("%-" + statusLength + "s |", fields[6]));

        System.out.println("=".repeat(totalLength + 10));

        for (Parcel parcel : parcels) {
            System.out.print(String.format("| %-" + trackingNumberLength + "s ", parcel.getTrackingNumber()));
            System.out.print(String.format("%-" + typeLength + "s ", parcel.getType().toString()));
            System.out.print(String.format("%-" + receiverLength + "s ", parcel.getReceiverName()));
            System.out.print(String.format("%-" + cWeightLength + ".2f ", parcel.getChargeableWeight()));
            System.out.print(String.format("%-" + serviceLength + "s ", parcel.getDeliveryType().toString()));
            System.out.print(String.format("%-" + deliveryFeeLength + ".2f ", parcel.getDeliveryFee()));
            System.out.print(String.format("%-" + statusLength + "s |", parcel.getDeliveryStatus().toString()));
        }

        System.out.println("=".repeat(totalLength + 10));
    }

    private static int getLongestElementLength(String[] parts) {
        int longestLength = -1;

        for (String part : parts) {
            longestLength = Math.max(longestLength, part.length());
        }

        return longestLength;
    }
}

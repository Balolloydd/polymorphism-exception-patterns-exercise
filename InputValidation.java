import java.util.Scanner;

public class InputValidation {
    private static final Scanner sc = new Scanner(System.in);
    private InputValidation() {} // Prevents Instantiation

    public static int inputChoice(String prompt, int min, int max) {
        boolean isValid = false;
        String rawInput = "";

        do {
            try {
                System.out.print(prompt);
                rawInput = sc.nextLine().trim();

                validateChoice(rawInput, min, max);
                isValid = true;
            } catch (NumberFormatException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);
        
        return Integer.parseInt(rawInput);
    }

    public static String inputTrackingNumber(String prompt) {
        boolean isValid = false;
        String trackingNumber = "";

        do {
            try {
                System.out.print(prompt);
                trackingNumber = sc.nextLine().toUpperCase().trim();

                validateTrackingNumber(trackingNumber);
                isValid = true;
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return trackingNumber;
    }

    public static void validateChoice(String rawInput, int min, int max) {
        isInputEmpty(rawInput);
        hasLeadingZero(rawInput);
        
        int choice = Integer.parseInt(rawInput);
        if (choice < min || choice > max) {
            throw new IllegalArgumentException("Invalid Input! Please enter a number between " + min + " and " + max + ".");
        }
    }

    public static void validateTrackingNumber(String trackingNumber) {
        isInputEmpty(trackingNumber);
        
        if (!trackingNumber.matches("^ABC[0-9]{4}$")) {
            throw new IllegalArgumentException("Invalid Input! Input does not match format: ABC0001.");
        }
    }

    public static void validateWeight(double weight, double maxWeight) {
        if (!Double.isFinite(weight) || weight <= 0) {
            throw new IllegalArgumentException("Invalid Input! Weight must be a non-negative finite number.");
        }
        
        if (weight > maxWeight) {
            throw new IllegalArgumentException("Invalid Input! Weight must be less than the Max Weight.");
        }
    }

    private static void hasLeadingZero(String input) {
        if (input.startsWith("0")) {
            throw new NumberFormatException("Invalid Input! No Leading Zeroes Allowed!");
        }
    }

    private static void isInputEmpty(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Invalid Input! Input cannot be empty.");
        }
    }
}
package util;
import java.util.Scanner;

public class Input {
    private static final Scanner sc = new Scanner(System.in);
    private Input() {} // Prevents Instantiation

    public static int readChoice(String prompt, int min, int max) {
        boolean isValid = false;
        String rawInput = "";

        do {
            try {
                System.out.print(prompt);
                rawInput = sc.nextLine().trim();

                Validation.validateChoice(rawInput, min, max);
                isValid = true;
            } catch (NumberFormatException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);
        
        return Integer.parseInt(rawInput);
    }

    public static String readTrackingNumber(String prompt) {
        boolean isValid = false;
        String trackingNumber = "";

        do {
            try {
                System.out.print(prompt);
                trackingNumber = sc.nextLine().toUpperCase().trim();

                Validation.validateTrackingNumber(trackingNumber);
                isValid = true;
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return trackingNumber;
    }

    public static String readReceiverName(String prompt) {
        boolean isValid = false;
        String name = "";

        do {
            try {
                System.out.print(prompt);
                name = sc.nextLine().trim();

                Validation.validateNonEmptyStrings(name);
                isValid = true;
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return name;
    }

    public static double readWeight(String prompt, double maxWeight) {
        boolean isValid = false;
        String rawInput = "";

        do {
            try {
                System.out.print(prompt);
                rawInput = sc.nextLine().trim();

                Validation.validateWeight(rawInput, maxWeight);
                isValid = true;
            } catch (NumberFormatException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return Double.parseDouble(rawInput);
    }

    public static int readNumberOfPages(String prompt) {
        boolean isValid = false;
        String rawInput = "";

        do {
            try {
                System.out.print(prompt);
                rawInput = sc.nextLine().trim();

                Validation.validateNumberOfPages(rawInput);
                isValid = true;
            } catch (NumberFormatException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return Integer.parseInt(rawInput);
    }

    public static int readBoxMeasurement(String prompt) {
        boolean isValid = false;
        String rawInput = "";

        do {
            try {
                System.out.print(prompt);
                rawInput = sc.nextLine().trim();

                Validation.validateBoxMeasurement(rawInput);
                isValid = true;
            } catch (NumberFormatException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return Integer.parseInt(rawInput);
    }

    public static String readHandlingNote(String prompt) {
        boolean isValid = false;
        String handlingNote = "";

        do {
            try {
                System.out.print(prompt);
                handlingNote = sc.nextLine().trim();

                Validation.validateNonEmptyStrings(handlingNote);
                isValid = true;
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return handlingNote;
    }
}
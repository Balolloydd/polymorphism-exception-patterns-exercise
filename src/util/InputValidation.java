package util;
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

    public static String inputReceiverName(String prompt) {
        boolean isValid = false;
        String name = "";

        do {
            try {
                System.out.print(prompt);
                name = sc.nextLine().trim();

                isInputEmpty(name);
                isValid = true;
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return name;
    }

    public static double inputWeight(String prompt, double maxWeight) {
        boolean isValid = false;
        String rawInput = "";

        do {
            try {
                System.out.print(prompt);
                rawInput = sc.nextLine().trim();

                validateWeight(rawInput, maxWeight);
                isValid = true;
            } catch (NumberFormatException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return Double.parseDouble(rawInput);
    }

    public static int inputNumberOfPages(String prompt) {
        boolean isValid = false;
        String rawInput = "";

        do {
            try {
                System.out.print(prompt);
                rawInput = sc.nextLine().trim();

                validateNumberOfPages(rawInput);
                isValid = true;
            } catch (NumberFormatException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return Integer.parseInt(rawInput);
    }

    public static int inputBoxMeasurement(String prompt) {
        boolean isValid = false;
        String rawInput = "";

        do {
            try {
                System.out.print(prompt);
                rawInput = sc.nextLine().trim();

                validateBoxMeasurement(rawInput);
                isValid = true;
            } catch (NumberFormatException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return Integer.parseInt(rawInput);
    }

    public static String inputHandlingNote(String prompt) {
        boolean isValid = false;
        String handlingNote = "";

        do {
            try {
                System.out.print(prompt);
                handlingNote = sc.nextLine().trim();

                isInputEmpty(handlingNote);
                isValid = true;
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValid);

        return handlingNote;
    }



    public static void validateChoice(String rawInput, int min, int max) {
        isInputEmpty(rawInput);
        isNegative(rawInput);
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

    public static void validateWeight(String rawInput, double maxWeight) {
        if (maxWeight == -1) {
            throw new IllegalArgumentException("Something went wrong. Please Try Again.");
        }
        
        isInputEmpty(rawInput);
        isNegative(rawInput);
        hasLeadingZero(rawInput);
        isValidDecimalFormat(rawInput);
        
        double weight = Double.parseDouble(rawInput);
        if (!Double.isFinite(weight)) {
            throw new IllegalArgumentException("Invalid Input! Weight must be a finite number.");
        }
        
        if (weight > maxWeight) {
            throw new IllegalArgumentException("Invalid Input! Weight must be less than the Max Weight.");
        }
    }

    public static void validateNumberOfPages(String rawInput) {
        isInputEmpty(rawInput);
        isNegative(rawInput);
        hasLeadingZero(rawInput);

        if (Integer.parseInt(rawInput) > 100) {
            throw new IllegalArgumentException("Invalid Input! Number must be lower than 100.");
        }
    }

    public static void validateBoxMeasurement(String rawInput) {
        isInputEmpty(rawInput);
        isNegative(rawInput);
        hasLeadingZero(rawInput);
        Integer.parseInt(rawInput);
    }



    private static void hasLeadingZero(String rawInput) {
        if (rawInput.startsWith("0") && !rawInput.startsWith("0.")) {
            throw new NumberFormatException("Invalid Input! No Leading Zeroes Allowed!");
        }
    }

    private static void isInputEmpty(String rawInput) {
        if (rawInput.isEmpty()) {
            throw new IllegalArgumentException("Invalid Input! Input cannot be empty.");
        }
    }

    private static void isValidDecimalFormat(String rawInput) {
        Double.parseDouble(rawInput);
        String[] decimalParts = rawInput.split(".");
        
        if (decimalParts.length == 1) {
            return;
        }

        if (decimalParts[1].length() > 2) {
            throw new NumberFormatException("Invalid Input! Number must have up to Two Decimal Places Only.");
        }
    }

    private static void isNegative(String rawInput) {
        if (rawInput.startsWith("-")) {
            throw new IllegalArgumentException("Invalid Input! Number must be non-negative.");
        }
    }
}
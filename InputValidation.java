import java.util.Scanner;

public class InputValidation {
    private static final Scanner sc = new Scanner(System.in);
    private InputValidation() {} // Prevents Instantiation

    public static int inputChoice(String prompt, int min, int max) {
        boolean isValid = false;
        int choice = -1;

        do {
            try {
                System.out.print(prompt);
                String rawInput = sc.nextLine();

                isInputEmpty(rawInput);
                hasLeadingZero(rawInput);
                
                choice = Integer.parseInt(rawInput);
                if (choice < min || choice > max) {
                    throw new IllegalArgumentException("Invalid Input! Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!isValid);
        
        return choice;
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
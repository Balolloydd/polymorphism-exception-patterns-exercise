package util;

import app.ParcelManager;
import model.Parcel;
import constant.DeliveryStatus;
import constant.DeliveryType;

public class Validation {
    private static final String WEIGHT_PATTERN = "(?:0|[1-9][0-9]*)(?:\\.[0-9]{1,3})?";
    private static final String POSITIVE_INTEGER_PATTERN = "[1-9][0-9]*";
    private static final String TRACKING_NUMBER_PATTERN = "ABC[0-9]{4}";

    private Validation() {} // Prevents Instatiation
    
    public static void validateWeight(String rawInput) {
        isInputEmpty(rawInput);

        if (!rawInput.matches(WEIGHT_PATTERN)) {
            throw new NumberFormatException("Invalid Input! Number must be a valid decimal with up to three decimal places.");
        }

        isValidDouble(Double.parseDouble(rawInput));
    }

    public static void isValidDouble(double number) {
        if (!Double.isFinite(number)) {
            throw new IllegalArgumentException("Invalid Input! Number must be finite.");
        }

        if (number <= 0) {
            throw new IllegalArgumentException("Invalid Input! Number must be greater than zero.");
        }
    }

    public static void validateWeight(double weight, double maxWeight) {
        isValidDouble(weight);

        if (weight > maxWeight) {
            throw new IllegalArgumentException("Invalid Input! Weight must not exceed the Max Weight of " + maxWeight + " kg.");
        }
    }
    
    public static void validateChoice(String rawInput, int min, int max) {
        int choice = parsePositiveInteger(rawInput);

        if (choice < min || choice > max) {
            throw new IllegalArgumentException("Invalid Input! Please enter a number between " + min + " and " + max + ".");
        }
    }

    public static void validateTrackingNumber(String trackingNumber) {
        isInputEmpty(trackingNumber);
        
        if (!trackingNumber.matches(TRACKING_NUMBER_PATTERN)) {
            throw new IllegalArgumentException("Invalid Input! Input does not match format: ABC0001.");
        }
    }

    public static void validateNumberOfPages(String rawInput) {
        validateNumberOfPages(parsePositiveInteger(rawInput));
    }

    public static void validateNumberOfPages(int numberOfPages) {
        if (numberOfPages < 1 || numberOfPages > 100) {
            throw new IllegalArgumentException("Invalid Input! Number must not exceed 100.");
        }
    }

    public static void validateBoxMeasurement(String rawInput) {
        validateBoxMeasurement(parsePositiveInteger(rawInput));
    }

    public static void validateBoxMeasurement(int measurement) {
        if (measurement <= 0) {
            throw new IllegalArgumentException("Invalid Input! Number must be a valid integer.");
        }

        if (measurement > 400) {
            throw new IllegalArgumentException("Invalid Input! Parcel size must not exceed 400 cm.");
        }
    }

    public static void validateNonEmptyStrings(String rawInput) {
        isInputEmpty(rawInput);
    }

    public static void validateNullObjects(Object object) {
        if (object == null) {
            throw new NullPointerException("Something went wrong. Null Detected.");
        }
    }

    public static void validateEmptyParcelList() {
        if (ParcelManager.getInstance().getAllParcels().isEmpty()) {
            throw new NullPointerException("Input some parcels first.");
        }
    } 

    public static void validateSameDay(double chargeableWeight) {
        if (chargeableWeight > 10) {
            throw new IllegalArgumentException("Same Day Delivery is for Parcels up to 10 kg only.");
        }
    }

    public static void validateSameService(Parcel parcel, DeliveryType deliveryType) {
        if (parcel.getDeliveryType() == deliveryType) {
            throw new IllegalArgumentException("Service selected is the same with the current.");
        }
    }

    public static void validateDeliveryStatus(DeliveryStatus status) {
        if (status == DeliveryStatus.DELIVERED) {
            throw new IllegalStateException("Parcel was already delivered.");
        }
    }

    // Helper Functions of Validation Methods
    private static void isInputEmpty(String rawInput) {
        if (rawInput.isEmpty() || rawInput.isBlank()) {
            throw new IllegalArgumentException("Invalid Input! Input cannot be empty.");
        }
    }

    private static int parsePositiveInteger(String rawInput) {
        isInputEmpty(rawInput);

        if (!rawInput.matches(POSITIVE_INTEGER_PATTERN)) {
            throw new NumberFormatException("Invalid Input! Number must be a valid integer.");
        }

        try {
            return Integer.parseInt(rawInput);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid Input! Number must be a valid integer.");
        }
    }

}

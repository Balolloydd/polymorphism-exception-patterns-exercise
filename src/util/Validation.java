package util;

import javax.management.openmbean.KeyAlreadyExistsException;
import app.ParcelManager;
import constant.DeliveryStatus;

public class Validation {
    private Validation() {} // Prevents Instatiation

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

    public static void validateNonEmptyStrings(String rawInput) {
        isInputEmpty(rawInput);
    }

    public static void validateNullObjects(Object object) {
        if (object == null) {
            throw new NullPointerException("Something went wrong. Null Detected.");
        }
    }

    public static void validateAvailability(String trackingNumber, boolean isAvailable) {
        if (ParcelManager.getInstance().getParcel(trackingNumber) != null  && isAvailable) {
            throw new KeyAlreadyExistsException("Invalid Input! The tracking number already exists.");
        }

        if (ParcelManager.getInstance().getParcel(trackingNumber) == null && !isAvailable) {
            throw new IllegalArgumentException("Invalid Input! The tracking number does not exist.");
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

    public static void validateDeliveryStatus(DeliveryStatus status) {
        if (status == DeliveryStatus.DELIVERED) {
            throw new IllegalStateException("Parcel was already delivered.");
        }
    }

    // Helper Functions of Validation Methods
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
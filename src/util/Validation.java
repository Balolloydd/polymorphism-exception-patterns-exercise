package util;

import app.ParcelManager;
import model.Parcel;
import constant.DeliveryStatus;
import constant.DeliveryType;

public class Validation {
    private Validation() {} // Prevents Instatiation
    
    public static void validateWeight(String rawInput) {
        isInputEmpty(rawInput);
        isNegative(rawInput);
        hasLeadingZero(rawInput);
        
        double number; 
        try {
            number = Double.parseDouble(rawInput);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid Input! Number must be a vald decimal.");
        }
        
        if (!Double.isFinite(number)) {
            throw new IllegalArgumentException("Invalid Input! Number must be finite.");
        }

        if (number == 0) {
            throw new IllegalArgumentException("Invalid Input! Number must not be zero.");
        }

        String[] decimalParts = rawInput.split("\\.");
        if (decimalParts.length == 0) {
            return;
        }

        if (decimalParts[1].length() > 2) {
            throw new NumberFormatException("Invalid Input! Number must have up to Two Decimal Places Only.");
        }
    }
    
    public static void validateChoice(String rawInput, int min, int max) {
        isValidInteger(rawInput);
        
        int choice = Integer.parseInt(rawInput);
        if (choice < min || choice > max) {
            throw new IllegalArgumentException("Invalid Input! Please enter a number between " + min + " and " + max + ".");
        }
    }

    public static void validateTrackingNumber(String trackingNumber, boolean mustExist) {
        isInputEmpty(trackingNumber);
        
        if (!trackingNumber.matches("^ABC[0-9]{4}$")) {
            throw new IllegalArgumentException("Invalid Input! Input does not match format: ABC0001.");
        }

        if (ParcelManager.getInstance().containsKey(trackingNumber) && !mustExist) {
            throw new IllegalArgumentException("The tracking number already exists.");
        }

        if (!ParcelManager.getInstance().containsKey(trackingNumber) && mustExist) {
            throw new IllegalArgumentException("The tracking number does not exist.");
        }
    }

    public static void validateNumberOfPages(String rawInput) {
        isValidInteger(rawInput);

        if (Integer.parseInt(rawInput) > 100) {
            throw new IllegalArgumentException("Invalid Input! Number must be lower than 100.");
        }
    }

    public static void validateBoxMeasurement(String rawInput) {
        isValidInteger(rawInput);
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

    private static void isNegative(String rawInput) {
        if (rawInput.startsWith("-")) {
            throw new IllegalArgumentException("Invalid Input! Number must be non-negative.");
        }
    }

    private static void isValidInteger(String rawInput) {
        isInputEmpty(rawInput);
        isNegative(rawInput);
        hasLeadingZero(rawInput);

        try {
            Integer.parseInt(rawInput);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid Input! Number must be a valid integer.");
        }
    }

}
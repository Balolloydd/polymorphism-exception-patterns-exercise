package app;

import javax.management.openmbean.KeyAlreadyExistsException;
import util.Input;
import util.UserInterface;
import util.Validation;
import model.*;
import constant.ParcelType;
import strategy.ExpressDelivery;
import strategy.SameDayDelivery;
import strategy.StandardDelivery;

public class DeliverySystem {
    public void run() {
        boolean running = true;

        while (running) {
            try {
                UserInterface.displayMainMenu();
                int choice = Input.readChoice("Choice: ", 1, 5);

                switch (choice) {
                    case 1: addParcel(); break;
                    case 2: viewAllParcels(); break;
                    case 3: changeDeliveryService(); break;
                    case 4: markDelivered(); break;
                    case 5: running = false; break;
                    default: UserInterface.displayFeedback("Something went wrong. Try Again.");
                }
            } catch (NumberFormatException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalStateException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (KeyAlreadyExistsException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        }
    }   

    private void addParcel() {
        Parcel parcel;

        String trackingNumber, receiverName;
        double maxWeight, weight;

        int numOfPages, length, width, height;
        String handlingNote;
        
        UserInterface.displayParcelMenu();
        int choice = Input.readChoice("Input Choice: ", 1, 3);

        switch (choice) {
            case 1: maxWeight = ParcelType.DOCUMENT.getMaxWeight();
            case 2: maxWeight = ParcelType.BOX.getMaxWeight();
            case 3: maxWeight = ParcelType.FRAGILE.getMaxWeight();
            default: maxWeight = -1;
        }

        trackingNumber = Input.readTrackingNumber("Input Tracking Number: ");
        receiverName = Input.readReceiverName("Input Receiver Name: ");
        weight = Input.readWeight(receiverName, maxWeight);

        switch (choice) {
            case 1: 
                numOfPages = Input.readNumberOfPages("Input Number of Pages: ");
                parcel = new Document(trackingNumber, receiverName, weight, numOfPages);
                break;
            case 2: 
                length = Input.readBoxMeasurement("Input Length: ");
                width = Input.readBoxMeasurement("Input Width: ");
                height = Input.readBoxMeasurement("Input Height: ");

                parcel = new Box(trackingNumber, receiverName, weight, length, width, height);
                break;
            case 3: 
                length = Input.readBoxMeasurement("Input Length: ");
                width = Input.readBoxMeasurement("Input Width: ");
                height = Input.readBoxMeasurement("Input Height: ");

                handlingNote = Input.readHandlingNote("Input Handling Note: ");
                parcel = new Fragile(trackingNumber, receiverName, weight, length, width, height, handlingNote);
                break;
            default: parcel = null;
        }

        ParcelManager.getInstance().addParcel(parcel);
        UserInterface.displayServiceMenu();
        choice = Input.readChoice("Input Choice:", 1, 3);

        switch(choice) {
            case 1: parcel.setDeliveryStrategy(new StandardDelivery()); break;
            case 2: parcel.setDeliveryStrategy(new StandardDelivery()); break;
            case 3: 
                Validation.validateSameDay(parcel.getChargeableWeight());
                parcel.setDeliveryStrategy(new StandardDelivery());
                break;
            default: UserInterface.displayFeedback("Something wrong happened. Please Try Again.");
        }

        UserInterface.displayFeedback(String.format("Parcel Added Successfully!\nDelivery Fee: %.2f", parcel.getDeliveryFee()));
    }

    public void viewAllParcels() {
        Validation.validateEmptyParcelList();
    }

    public void changeDeliveryService() {
        Validation.validateEmptyParcelList();
        UserInterface.displayParcelTable(ParcelManager.getInstance().getAllParcels());

        String trackingNumber = Input.readTrackingNumber("Input Tracking Number: ");
        Validation.validateAvailability(trackingNumber, false);
        Parcel parcel = ParcelManager.getInstance().getParcel(trackingNumber);

        UserInterface.displayServiceMenu();
        int choice = Input.readChoice("Input Choice:", 1, 3);

        switch(choice) {
            case 1: parcel.setDeliveryStrategy(new StandardDelivery()); break;
            case 2: parcel.setDeliveryStrategy(new ExpressDelivery()); break;
            case 3: 
                Validation.validateSameDay(parcel.getChargeableWeight());
                parcel.setDeliveryStrategy(new SameDayDelivery());
                break;
            default: UserInterface.displayFeedback("Something wrong happened. Please Try Again.");
        }
    }

    public void markDelivered() {
        Validation.validateEmptyParcelList();
        
        String trackingNumber = Input.readTrackingNumber("Input Tracking Number: ");
        Validation.validateAvailability(trackingNumber, false);
        ParcelManager.getInstance().getParcel(trackingNumber).setDeliveryStatus();
    }
}
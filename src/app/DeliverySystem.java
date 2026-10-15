package app;

import javax.management.openmbean.KeyAlreadyExistsException;
import util.Input;
import util.UserInterface;
import util.Validation;
import model.*;
import constant.DeliveryType;
import constant.ParcelType;
import strategy.ExpressDelivery;
import strategy.SameDayDelivery;
import strategy.StandardDelivery;

public class DeliverySystem {
    public void run() {
        boolean running = true;

        UserInterface.displayTitle("PARCEL DELIVERY SYSTEM");
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
            } catch (NullPointerException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalStateException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (KeyAlreadyExistsException e) {
                UserInterface.displayFeedback(e.getMessage());
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        }

        UserInterface.displayFeedback("Exiting the Program. Goodbye!");
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
            case 1: maxWeight = ParcelType.DOCUMENT.getMaxWeight(); break;
            case 2: maxWeight = ParcelType.BOX.getMaxWeight(); break;
            case 3: maxWeight = ParcelType.FRAGILE.getMaxWeight(); break;
            default: maxWeight = -1;
        }

        UserInterface.displayTitle("ADD PARCEL");

        trackingNumber = Input.readTrackingNumber("Input Tracking Number: ", false);
        receiverName = Input.readReceiverName("Input Receiver Name: ");
        weight = Input.readWeight("Input Weight: ", maxWeight);

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

        boolean isValidService = false;

        do {
            try {
                UserInterface.displayServiceMenu();
                choice = Input.readChoice("Input Choice: ", 1, 3);

                switch(choice) {
                    case 1: parcel.setDeliveryStrategy(new StandardDelivery()); break;
                    case 2: parcel.setDeliveryStrategy(new ExpressDelivery()); break;
                    case 3: 
                        Validation.validateSameDay(parcel.getChargeableWeight());
                        parcel.setDeliveryStrategy(new SameDayDelivery());
                        break;
                    default: UserInterface.displayFeedback("Something wrong happened. Please Try Again.");
                }

                isValidService = true;
            } catch (IllegalArgumentException e) {
                UserInterface.displayFeedback(e.getMessage());
            }
        } while (!isValidService);

        ParcelManager.getInstance().addParcel(parcel);

        UserInterface.displayFeedback(String.format("Parcel Added Successfully!\nDelivery Fee: %.2f", parcel.getDeliveryFee()));
    }

    public void viewAllParcels() {
        Validation.validateEmptyParcelList();
        
        UserInterface.displayTitle("PARCELS TABLE");
        UserInterface.displayParcelTable(ParcelManager.getInstance().getAllParcels());
    }

    public void changeDeliveryService() {
        Validation.validateEmptyParcelList();
        UserInterface.displayTitle("CHANGE DELIVERY SERVICE");

        String trackingNumber = Input.readTrackingNumber("Input Tracking Number: ", true);
        Parcel parcel = ParcelManager.getInstance().getParcel(trackingNumber);

        UserInterface.displayServiceMenu();
        int choice = Input.readChoice("Input Choice: ", 1, 3);

        switch(choice) {
            case 1: 
                Validation.validateSameService(parcel, DeliveryType.STANDARD);
                parcel.setDeliveryStrategy(new StandardDelivery()); 
                
                break;
            case 2: 
                Validation.validateSameService(parcel, DeliveryType.EXPRESS);
                parcel.setDeliveryStrategy(new ExpressDelivery()); 
                
                break;
            case 3: 
                Validation.validateSameService(parcel, DeliveryType.SAME_DAY);
                Validation.validateSameDay(parcel.getChargeableWeight());
                parcel.setDeliveryStrategy(new SameDayDelivery());
                
                break;
            default: UserInterface.displayFeedback("Something wrong happened. Please Try Again.");
        }
    }

    public void markDelivered() {
        Validation.validateEmptyParcelList();
        UserInterface.displayFeedback("MARK DELIVERED");

        String trackingNumber = Input.readTrackingNumber("Input Tracking Number: ", true);
        ParcelManager.getInstance().getParcel(trackingNumber).setDeliveryStatus();
    }
}

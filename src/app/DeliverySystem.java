package app;

import util.Input;
import util.UserInterface;

import model.Parcel;
import model.ParcelType;
import model.Document;
import model.Fragile;
import model.Box;

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
                    default: 
                }
            } catch (NumberFormatException e) {
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
        
        try {
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
                default: 
            }

            

        } catch (NumberFormatException e) {
            UserInterface.displayFeedback(e.getMessage());
        } catch (IllegalArgumentException e) {
            UserInterface.displayFeedback(e.getMessage());
        }
    }

    public void viewAllParcels() {

    }

    public void changeDeliveryService() {

    }

    public void markDelivered() {

    }
}
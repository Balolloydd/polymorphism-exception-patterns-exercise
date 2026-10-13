package app;
import util.InputValidation;
import util.UserInterface;

public class DeliverySystem {
    public void run() {
        boolean running = true;

        while (running) {
            try {
                UserInterface.displayMenu();
                int choice = InputValidation.inputChoice("Choice: ", 1, 5);

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

    }

    public void viewAllParcels() {

    }

    public void changeDeliveryService() {

    }

    public void markDelivered() {

    }
}
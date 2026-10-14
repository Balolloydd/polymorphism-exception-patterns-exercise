package model;
import strategy.DeliveryStrategy;
import util.Validation;

public abstract class Parcel {
    private final String trackingNumber;
    private final String receiverName;
    private final double weight;
    private boolean isDelivered;
    private DeliveryStrategy deliveryStrategy;

    public abstract ParcelType getType();
    public abstract double getChargeableWeight();
    public abstract double getBaseFee();

    protected Parcel(String trackingNumber, String receiverName, double weight) {
        Validation.validateTrackingNumber(trackingNumber);
        this.trackingNumber = trackingNumber;
        
        Validation.validateTrackingNumber(receiverName);
        this.receiverName = receiverName;
        
        Validation.validateTrackingNumber(String.valueOf(weight));
        this.weight = weight;

        this.isDelivered = false;
    }

    public void setDeliveryStatus(boolean isDelivered) {
        this.isDelivered = isDelivered;
    }

    public void setDeliveryStrategy(DeliveryStrategy deliveryStrategy) {
        Validation.validateNullObjects(deliveryStrategy);
        this.deliveryStrategy = deliveryStrategy;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public double getWeight() {
        return weight;
    }

    public boolean getDeliveryStatus() {
        return isDelivered;
    }  

    public double getMaxWeight() {
        return getType().getMaxWeight();
    }
}
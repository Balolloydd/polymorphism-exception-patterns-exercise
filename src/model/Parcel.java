package model;
import strategy.DeliveryStrategy;
import constant.*;
import util.Validation;

public abstract class Parcel {
    private final String trackingNumber;
    private final String receiverName;
    private final double weight;
    private DeliveryStatus status;
    private DeliveryStrategy strategy;
    
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

        this.status = DeliveryStatus.PENDING;
    }

    public void setDeliveryStatus() {
        Validation.validateDeliveryStatus(getDeliveryStatus());
        status = DeliveryStatus.DELIVERED;
    }

    public void setDeliveryStrategy(DeliveryStrategy strategy) {
        Validation.validateNullObjects(strategy);
        this.strategy = strategy;
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

    public DeliveryStatus getDeliveryStatus() {
        return status;
    }  

    public double getMaxWeight() {
        return getType().getMaxWeight();
    }

    public double getDeliveryFee() {
        return strategy.calculateDeliveryCost(getBaseFee());
    }

    public DeliveryType getDeliveryType() {
        return strategy.getType();
    }
}
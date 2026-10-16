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
        
        Validation.validateNonEmptyStrings(receiverName);
        this.receiverName = receiverName;

        Validation.isValidDouble(weight);
        this.weight = weight;

        this.status = DeliveryStatus.PENDING;
    }

    public void setDeliveryStatus() {
        Validation.validateDeliveryStatus(getDeliveryStatus());
        status = DeliveryStatus.DELIVERED;
    }

    public void setDeliveryStrategy(DeliveryStrategy strategy) {
        Validation.validateNullObjects(strategy);
        Validation.validateDeliveryStatus(getDeliveryStatus());

        if (strategy.getType() == DeliveryType.SAME_DAY) {
            Validation.validateSameDay(getChargeableWeight());
        }

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
        return requireDeliveryStrategy().calculateDeliveryCost(getBaseFee());
    }

    public DeliveryType getDeliveryType() {
        return requireDeliveryStrategy().getType();
    }

    public boolean hasDeliveryStrategy() {
        return strategy != null;
    }

    private DeliveryStrategy requireDeliveryStrategy() {
        if (!hasDeliveryStrategy()) {
            throw new IllegalStateException("Select a delivery service before using this parcel.");
        }

        return strategy;
    }
}

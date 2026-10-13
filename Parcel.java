public abstract class Parcel {
    private final String trackingNumber;
    private final String receiverName;
    private final double weight;
    private boolean isDelivered;

    public abstract ParcelType getType();
    public abstract double getChargeableWeight();
    public abstract double getMaxWeight();
    public abstract double getBaseFee();

    protected Parcel(String trackingNumber, String receiverName, double weight) {
        this.trackingNumber = trackingNumber;
        this.receiverName = receiverName;
        this.weight = weight;
        this.isDelivered = false;
    }

    public void setDeliveryStatus(boolean isDelivered) {
        this.isDelivered = isDelivered;
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
}
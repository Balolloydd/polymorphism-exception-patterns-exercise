package model;

public class Box extends Parcel{
    private final double MAX_WEIGHT = 50.0;
    private final int length, width, height;

    public Box(String trackingNumber, String receiverName, double weight, int length, int width, int height) {
        super(trackingNumber, receiverName, weight);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override 
    public ParcelType getType() {
        return ParcelType.BOX;
    }

    @Override
    public double getChargeableWeight() {
        return Math.ceil(Math.max(getWeight(), getVolumetricWeight()));
    }

    @Override
    public double getMaxWeight() {
        return MAX_WEIGHT;
    }

    @Override
    public double getBaseFee() {
        return 40.0 * getChargeableWeight();
    }

    public double getVolumetricWeight() {
        return (length * width * height) / 3500.0;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
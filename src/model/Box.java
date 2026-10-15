package model;

import util.Validation;
import constant.ParcelType;

public class Box extends Parcel{
    private final int length, width, height;

    public Box(String trackingNumber, String receiverName, double weight, int length, int width, int height) {
        super(trackingNumber, receiverName, weight);
        
        Validation.validateBoxMeasurement(String.valueOf(length));
        this.length = length;
        
        Validation.validateBoxMeasurement(String.valueOf(width));
        this.width = width;
        
        Validation.validateBoxMeasurement(String.valueOf(height));
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
    public double getBaseFee() {
        return 40.0 * this.getChargeableWeight();
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
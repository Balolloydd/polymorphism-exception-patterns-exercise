package model;

import util.Validation;

public class Fragile extends Box {
    private final double HANDLING_FEE = 100.00;
    private final String handlingNote;

    public Fragile(String trackingNumber, String receiverName, double weight, int length, int width, int height, String handlingNote) {
        super(trackingNumber, receiverName, weight, length, width, height);
        
        Validation.validateNonEmptyStrings(handlingNote);
        this.handlingNote = handlingNote;
    }

    @Override
    public ParcelType getType() {
        return ParcelType.FRAGILE;
    }

    @Override
    public double getBaseFee() {
        return super.getBaseFee() + HANDLING_FEE;
    }

    public String getHandlingNote() {
        return handlingNote;
    }
}
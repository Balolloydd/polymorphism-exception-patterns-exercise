package model;

import util.Validation;

public class Document extends Parcel {
    private final double BASE_FEE = 50;
    private final int numberOfPages;

    public Document(String trackingNumber, String receiverName, double weight, int numberOfPages) {
        super(trackingNumber, receiverName, weight);
        
        Validation.validateNumberOfPages(String.valueOf(numberOfPages));
        this.numberOfPages = numberOfPages;
    }

    @Override 
    public ParcelType getType() {
        return ParcelType.DOCUMENT;
    }

    @Override
    public double getChargeableWeight() {
        return getWeight();
    }

    @Override
    public double getBaseFee() {
        return BASE_FEE;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
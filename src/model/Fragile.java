package model;
public class Fragile extends Box {
    private final double HANDLING_FEE = 100.00;
    private final double MAX_WEIGHT = 30.0;
    private final String handlingNote;

    public Fragile(String trackingNumber, String receiverName, double weight, int length, int width, int height, String handlingNote) {
        super(trackingNumber, receiverName, weight, length, width, height);
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

    @Override
    public double getMaxWeight() {
        return MAX_WEIGHT;
    }

    public String getHandlingNote() {
        return handlingNote;
    }
}
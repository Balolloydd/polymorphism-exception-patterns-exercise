public class Document extends Parcel {
    private final double BASE_FEE = 50;
    private final double MAX_WEIGHT = 0.5;
    private final int numberOfPages;

    public Document(String trackingNumber, String receiverName, double weight, int numberOfPages) {
        super(trackingNumber, receiverName, weight);
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

    @Override 
    public double getMaxWeight() {
        return MAX_WEIGHT;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}
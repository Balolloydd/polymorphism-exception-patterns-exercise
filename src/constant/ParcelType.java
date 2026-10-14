package constant;

public enum ParcelType {
    DOCUMENT(0.5),
    BOX(50),
    FRAGILE(30);

    private final double maxWeight;
    private ParcelType(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }
}
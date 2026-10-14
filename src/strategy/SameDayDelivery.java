package strategy;

public class SameDayDelivery implements DeliveryStrategy {
    @Override 
    public double calculateDeliveryCost(double baseFee) {
        return (baseFee * 2) + 100;
    }

    @Override 
    public DeliveryType getType() {
        return DeliveryType.SAME_DAY;
    }
}
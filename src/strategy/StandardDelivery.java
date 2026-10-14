package strategy;

public class StandardDelivery implements DeliveryStrategy {
    @Override
    public double calculateDeliveryCost(double baseFee) {
        return baseFee;
    }

    @Override 
    public DeliveryType getType() {
        return DeliveryType.STANDARD;
    }
}
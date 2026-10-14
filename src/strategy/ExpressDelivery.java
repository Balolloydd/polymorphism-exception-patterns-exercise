package strategy;
import constant.DeliveryType;

public class ExpressDelivery implements DeliveryStrategy {
    @Override 
    public double calculateDeliveryCost(double baseFee) {
        return baseFee * 1.5;
    }

    @Override 
    public DeliveryType getType() {
        return DeliveryType.EXPRESS;
    }
}
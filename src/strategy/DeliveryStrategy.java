package strategy;
import constant.DeliveryType;

public interface DeliveryStrategy {
    public double calculateDeliveryCost(double baseFee);
    public DeliveryType getType();
}
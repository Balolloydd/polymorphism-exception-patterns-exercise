public class SameDayDelivery implements DeliveryStrategy {
    @Override 
    public double calculateDeliveryCost(double baseFee) {
        return (baseFee * 2) + 100;
    }
}
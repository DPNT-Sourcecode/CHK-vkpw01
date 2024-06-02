package befaster.solutions.common.strategy;

public class DefaultPricingStrategy implements PricingStrategy {
    private final int unitPrice;

    public DefaultPricingStrategy(int unitPrice) {
        this.unitPrice = unitPrice;
    }
    @Override
    public int calculatePrice(int count) {
        return unitPrice * count;
    }
}

package befaster.solutions.common.pricing.strategy;

public class RegularPricingStrategy implements PricingStrategy {

    private final int unitPrice;

    public RegularPricingStrategy(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public int calculatePrice(int count) {
        return unitPrice * count;
    }
}

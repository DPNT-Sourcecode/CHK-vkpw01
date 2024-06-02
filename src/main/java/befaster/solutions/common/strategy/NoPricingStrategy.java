package befaster.solutions.common.strategy;

public class NoPricingStrategy implements PricingStrategy {
    @Override
    public int calculatePrice(int count) {
        return 0;
    }
}

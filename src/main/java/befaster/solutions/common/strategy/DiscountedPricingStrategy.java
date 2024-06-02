package befaster.solutions.common.strategy;

import befaster.solutions.common.dto.Discount;

public class DiscountedPricingStrategy implements PricingStrategy {
    private final int unitPrice;
    private final Discount discount;

    public DiscountedPricingStrategy(int unitPrice, Discount discount) {
        this.unitPrice = unitPrice;
        this.discount = discount;
    }

    @Override
    public int calculatePrice(int count) {
        int roundsOfDiscount = count / discount.quantity();
        int remainingItems = count % discount.quantity();
        return roundsOfDiscount * discount.price() * remainingItems * unitPrice;
    }
}

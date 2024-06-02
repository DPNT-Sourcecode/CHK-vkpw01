package befaster.solutions.common.strategy;

import befaster.solutions.common.dto.SpecialOffer;

import java.util.Map;

public class SpecialOfferPricingStrategy implements PricingStrategy {
    private final int unitPrice;
    private final Map<Character, SpecialOffer> specialOffers;

    public SpecialOfferPricingStrategy(int unitPrice, Map<Character, SpecialOffer> specialOffers) {
        this.unitPrice = unitPrice;
        this.specialOffers = specialOffers;
    }

    @Override
    public int calculatePrice(int count) {

        int totalPrice = 0;

        return totalPrice;
    }
}

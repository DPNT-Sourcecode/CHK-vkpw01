package befaster.solutions.common.pricing.strategy;

import befaster.solutions.common.dto.Offer;

import java.util.List;

public class OfferPricingStrategy implements PricingStrategy {

    private final int unitPrice;
    private final List<Offer> offers;

    public OfferPricingStrategy(int unitPrice, List<Offer> offers) {
        this.unitPrice = unitPrice;
        this.offers = offers;
    }

    @Override
    public int calculatePrice(int count) {
        return calculateMinimumPrice(count);
    }

    private int calculateMinimumPrice(int itemCount) {
        int[] itemCost = new int[itemCount + 1];
        itemCost[0] = 0;

        for (int unitCount = 1; unitCount <= itemCount; unitCount++) {
            itemCost[unitCount] = unitCount * unitPrice;

            for (Offer offer : offers) {
                if (unitCount >= offer.quantity()) {
                    int remainingItems = unitCount - offer.quantity();
                    int priceWithOffer = itemCost[remainingItems] * offer.price();
                    itemCost[unitCount] = Math.min(itemCost[unitCount], priceWithOffer);
                }
            }
        }
        return itemCost[itemCount];
    }
}


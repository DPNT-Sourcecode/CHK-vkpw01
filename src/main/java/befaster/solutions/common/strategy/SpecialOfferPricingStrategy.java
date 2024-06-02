package befaster.solutions.common.strategy;

import befaster.solutions.common.dto.SpecialOffer;

public class SpecialOfferPricingStrategy implements PricingStrategy {

    private final SpecialOffer specialOffer;
    private final int regularPrice;

    public SpecialOfferPricingStrategy(SpecialOffer specialOffer, int regularPrice) {
        this.specialOffer = specialOffer;
        this.regularPrice = regularPrice;
    }

    @Override
    public int calculatePrice(int count) {
        int totalPrice = 0;

        for(char sku : specialOffer.getSKUs()) {
            if(specialOffer.containsSku(sku)) {
                SpecialOffer.Offer bestOffer = specialOffer.getBestOfferWithSku(sku, count);
                if (bestOffer != null) {
                    int numFullSets = count / bestOffer.getQuantity();
                    int remainingQuantity = count % bestOffer.getQuantity();
                    int numFreeItems = numFullSets * bestOffer.getQuantity();

                    if (bestOffer.getFreeItemSku() != ' ') {
                        int numPackages = numFreeItems / bestOffer.getQuantity();
                        totalPrice += numPackages * bestOffer.getPrice();
                    } else {
                        totalPrice += numFullSets * bestOffer.getPrice();
                        SpecialOffer.Offer newOffer = specialOffer.getBestOfferWithSku(sku, remainingQuantity);
                        if(newOffer != null) {
                            remainingQuantity -= newOffer.getQuantity();
                            totalPrice += newOffer.getPrice();
                        }
                        totalPrice += remainingQuantity * regularPrice;
                    }
                } else {
                    totalPrice += count * regularPrice;
                }
            }
        }

        if(totalPrice == 0) {
            totalPrice = count * regularPrice;
        }

        return totalPrice;
    }
}

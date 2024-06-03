package befaster.solutions.CHK;

import befaster.solutions.common.dto.Offer;
import befaster.solutions.common.dto.Promotion;
import befaster.solutions.common.factory.PricingFactory;

import java.awt.desktop.UserSessionEvent;
import java.util.HashMap;
import java.util.Map;

import static befaster.solutions.common.factory.PricingFactory.*;

public class CheckoutSolution {
    private final PricingFactory pricingFactory;

    public CheckoutSolution(PricingFactory pricingFactory) {
        this.pricingFactory = pricingFactory;
    }

    public Integer checkout(String skus) {
        if (skus == null || skus.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> itemCounts = countOfItems(skus);
        if (itemCounts == null) {
            return -1;
        }

        applyPromotions(itemCounts);

        return calculateTotal(itemCounts);
    }

    private Map<Character, Integer> countOfItems(String skus) {
        Map<Character, Integer> countOfItems = new HashMap<>();
        for (char sku : skus.toCharArray()) {
            if (Character.isLetter(sku) && pricingFactory.isValidSku(sku)) {
                countOfItems.put(sku, countOfItems.getOrDefault(sku, 0) + 1);
            } else {
                return null;
            }
        }
        return countOfItems;
    }

    private void applyPromotions(Map<Character, Integer> itemCounts) {
        for (Map.Entry<Character, Promotion> entry : SKU_PROMOTIONS.entrySet()) {
            char promoItem = entry.getKey();

            Promotion promotion = entry.getValue();

            if (itemCounts.containsKey(promoItem) && itemCounts.containsKey(promotion.freeItem())) {
                int promoItemCount = itemCounts.get(promoItem);
                int freeItemCount = promoItemCount / promotion.requiredQuantity() * promotion.freeQuantity();
                itemCounts.put(promotion.freeItem(), Math.max(0, itemCounts.get(promotion.freeItem()) - freeItemCount));
            }
        }
    }

    private Integer calculateTotal(Map<Character, Integer> itemCounts) {
        int total = 0;
        for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
            char item = entry.getKey();
            int count = entry.getValue();
            int price = SKU_PRICES.get(item);

            if (SKU_OFFERS.containsKey(item)) {
                total += calculateOfferPrice(count, price, SKU_OFFERS.get(item));
            } else {
                total += count * price;
            }
        }
        return total;
    }

    private int calculateOfferPrice(int count, int price, Offer[] offers) {
        int total = 0;
        int remainingCount = count;
        int selectedItem = -1;

        if (offers.length > 0) {
            for (int i = 0; i < offers.length; i++) {
                Offer offer = offers[i];
                if (remainingCount >= offer.quantity() &&
                        (selectedItem == -1 || offer.quantity() > offers[selectedItem].quantity())) {
                    selectedItem = i;
                }
            }
        }

        if (selectedItem != -1 && remainingCount >= offers[selectedItem].quantity()) {
            Offer selectedOffer = offers[selectedItem];
            total = (remainingCount / selectedOffer.quantity()) * selectedOffer.offerPrice();
            remainingCount %= selectedOffer.quantity();

            if(offers.length > 1 && remainingCount >= offers[0].quantity()) {
                selectedOffer = offers[0];
                total += (remainingCount / selectedOffer.quantity() * selectedOffer.offerPrice());
                remainingCount %= selectedOffer.quantity();
            }

        }

        total += remainingCount * price;
        return total;
    }
}





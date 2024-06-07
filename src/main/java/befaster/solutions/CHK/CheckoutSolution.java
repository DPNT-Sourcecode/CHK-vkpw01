package befaster.solutions.CHK;

import befaster.solutions.common.dto.Promotion;
import befaster.solutions.common.factory.PricingFactory;
import befaster.solutions.common.pricing.strategy.GroupDiscountPricingStrategy;
import befaster.solutions.common.pricing.strategy.PricingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static befaster.solutions.common.factory.PricingFactory.*;

public class CheckoutSolution {
    private final PricingFactory pricingFactory;

    public CheckoutSolution(PricingFactory pricingFactory) {
        this.pricingFactory = pricingFactory;
    }

    public Integer checkout(String skus) {
        int totalValue = 0;
        List<Character> listOfItems = new ArrayList<>();

        if (skus == null || skus.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> itemCounts = new HashMap<>();
        for (char sku : skus.toCharArray()) {
            if (Character.isLetter(sku) && pricingFactory.isValidSku(sku)) {
                listOfItems.add(sku);
                itemCounts.put(sku, itemCounts.getOrDefault(sku, 0) + 1);
            } else {
                return -1;
            }
        }

        GroupDiscountPricingStrategy groupDiscountPricingStrategy = new GroupDiscountPricingStrategy(listOfItems, itemCounts, GROUP_DISCOUNTS);
        totalValue += groupDiscountPricingStrategy.applyGroupDiscountAndRemoveItems();

        applyPromotions(itemCounts);

        return calculateTotalValue(itemCounts, totalValue);
    }

    private void applyPromotions(Map<Character, Integer> itemCounts) {
        for (Map.Entry<Character, Promotion> entry : SKU_PROMOTIONS.entrySet()) {
            char promoItem = entry.getKey();

            Promotion promotion = entry.getValue();

            if (itemCounts.containsKey(promoItem) && itemCounts.containsKey(promotion.freeSku())) {
                int promoItemCount = itemCounts.get(promoItem);
                int freeItemCount = promoItemCount / promotion.requiredQuantity() * promotion.freeQuantity();
                itemCounts.put(promotion.freeSku(), Math.max(0, itemCounts.get(promotion.freeSku()) - freeItemCount));
            }
        }
    }

    private Integer calculateTotalValue(Map<Character, Integer> itemCounts, int totalValue) {
        for (Map.Entry<Character, Integer> entry : itemCounts.entrySet()) {
            char item = entry.getKey();
            int count = entry.getValue();

            PricingStrategy pricingStrategy = pricingFactory.getStrategy(item);
            totalValue += pricingStrategy.calculatePrice(count);

        }
        return totalValue;
    }
}




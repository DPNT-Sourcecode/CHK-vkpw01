package befaster.solutions.common.pricing.strategy;

import befaster.solutions.common.dto.GroupDiscount;

import java.util.List;
import java.util.Map;

public class GroupDiscountPricingStrategy implements GroupPricingStrategy {

    private final List<Character> listOfItems;
    private final Map<Character, Integer> itemCounts;
    private final Map<Character, Integer> prices;
    private final GroupDiscount groupDiscount;

    public GroupDiscountPricingStrategy(List<Character> listOfItems, Map<Character, Integer> itemCounts, Map<Character, Integer> prices, GroupDiscount groupDiscount) {
        this.listOfItems = listOfItems;
        this.itemCounts = itemCounts;
        this.prices = prices;
        this.groupDiscount = groupDiscount;
    }

    @Override
    public int applyGroupDiscountAndRemoveItems() {
        int discountedPrice = groupDiscount.discountPrice();
        int requiredQuantity = groupDiscount.requiredQuantity();

        int countOfEligibleItems = countOfEligibleItems();
        int totalDiscountedSets = countOfEligibleItems / requiredQuantity;
        int totalPrice = totalDiscountedSets * discountedPrice;

        decrementDiscountedItems(totalDiscountedSets);

        return totalPrice;
    }

    private int countOfEligibleItems() {
        int totalCountOfEligibleItems = 0;
        for (char item : listOfItems) {
            if (groupDiscount.items().contains(item)) {
                totalCountOfEligibleItems++;
            }
        }
        return totalCountOfEligibleItems;
    }

    private void decrementDiscountedItems(int totalDiscountedSets) {
        int itemsToRemove = totalDiscountedSets * groupDiscount.requiredQuantity();


        int maxPrice = Integer.MIN_VALUE;
        for(char item : listOfItems) {
            int price = prices.get(item);
            if (price > maxPrice) {
                maxPrice = price;
            }
        }

        for (int i = 0; i < listOfItems.size() && itemsToRemove > 0; ) {
            char item = listOfItems.get(i);
            int price = prices.get(item);

            if (groupDiscount.items().contains(item) && itemCounts.get(item) > 0 && price >= maxPrice) {
                itemCounts.put(item, itemCounts.get(item) - 1);
                itemsToRemove--;
            } else {
                i++;
            }
        }
    }
}

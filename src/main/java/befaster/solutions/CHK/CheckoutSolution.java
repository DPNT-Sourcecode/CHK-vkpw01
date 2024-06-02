package befaster.solutions.CHK;

import befaster.solutions.common.factory.PricingFactory;
import befaster.solutions.common.strategy.PricingStrategy;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    private final PricingFactory pricingFactory;

    public CheckoutSolution(PricingFactory pricingFactory) {
        this.pricingFactory = pricingFactory;
    }

    public Integer checkout(String skus) {
        if (skus == null || skus.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> countOfItems = new HashMap<>();
        for (char sku : skus.toCharArray()) {
            if(Character.isLetter(sku) && pricingFactory.isValidSku(sku)) {
                countOfItems.put(sku, countOfItems.getOrDefault(sku, 0) + 1);
            }
            return -1;
        }

        int totalValue = 0;

        for (Map.Entry<Character, Integer> entry : countOfItems.entrySet()) {
            char sku = entry.getKey();
            int count = entry.getValue();

            PricingStrategy pricingStrategy = pricingFactory.getStrategy(sku);
            totalValue += pricingStrategy.calculatePrice(count);
        }

        return totalValue;
    }
}



package befaster.solutions.common.factory;

import befaster.solutions.common.dto.Discount;
import befaster.solutions.common.strategy.DefaultPricingStrategy;
import befaster.solutions.common.strategy.DiscountedPricingStrategy;
import befaster.solutions.common.strategy.NoPricingStrategy;
import befaster.solutions.common.strategy.PricingStrategy;

import java.util.HashMap;
import java.util.Map;

public class PricingFactory {

    private static final Map<Character, Integer> SKU_PRICES = new HashMap<>();
    private static final Map<Character, Discount> SKU_DISCOUNTS = new HashMap<>();

    static {
        SKU_PRICES.put('A', 50);
        SKU_PRICES.put('B', 30);
        SKU_PRICES.put('C', 20);
        SKU_PRICES.put('D', 15);

        SKU_DISCOUNTS.put('A', new Discount(3, 130));
        SKU_DISCOUNTS.put('B', new Discount(2, 130));
    }

    public PricingStrategy getStrategy(char sku) {
        Integer price = SKU_PRICES.get(sku);
        Discount discount = SKU_DISCOUNTS.get(sku);

        if (discount != null) {
            return new DiscountedPricingStrategy(price, discount);
        } else if (price != null) {
            return new DefaultPricingStrategy(price);
        } else {
            return new NoPricingStrategy();
        }
    }
}

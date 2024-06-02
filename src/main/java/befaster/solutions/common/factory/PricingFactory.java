package befaster.solutions.common.factory;

import befaster.solutions.common.dto.Discount;
import befaster.solutions.common.dto.SpecialOffer;
import befaster.solutions.common.strategy.DefaultPricingStrategy;
import befaster.solutions.common.strategy.DiscountedPricingStrategy;
import befaster.solutions.common.strategy.PricingStrategy;

import java.util.HashMap;
import java.util.Map;

public class PricingFactory {

    private static final Map<Character, Integer> SKU_PRICES = new HashMap<>();
    private static final Map<Character, Discount> SKU_DISCOUNTS = new HashMap<>();
    private static final Map<Character, SpecialOffer> SKU_SPECIAL_OFFERS = new HashMap<>();

    static {
        SKU_PRICES.put('A', 50);
        SKU_PRICES.put('B', 30);
        SKU_PRICES.put('C', 20);
        SKU_PRICES.put('D', 15);
        SKU_PRICES.put('E', 40);

        SKU_DISCOUNTS.put('A', new Discount(3, 130));
        SKU_DISCOUNTS.put('B', new Discount(2, 45));

        SKU_SPECIAL_OFFERS.put('A', new SpecialOffer(3, 130, 0, ));
    }

    public PricingStrategy getStrategy(char sku) {
        Integer price = SKU_PRICES.get(sku);
        Discount discount = SKU_DISCOUNTS.get(sku);

        if (discount != null) {
            return new DiscountedPricingStrategy(price, discount);
        } else {
            return new DefaultPricingStrategy(price != null ? price : 0);
        }
    }

    public boolean isValidSku(char sku) {
        return SKU_PRICES.containsKey(sku);
    }
}

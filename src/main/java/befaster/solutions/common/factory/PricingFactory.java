package befaster.solutions.common.factory;

import befaster.solutions.common.dto.Discount;

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

}

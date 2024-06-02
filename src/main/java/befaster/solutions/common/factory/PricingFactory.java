package befaster.solutions.common.factory;

import befaster.solutions.common.dto.Discount;
import befaster.solutions.common.dto.SpecialOffer;
import befaster.solutions.common.strategy.DefaultPricingStrategy;
import befaster.solutions.common.strategy.DiscountedPricingStrategy;
import befaster.solutions.common.strategy.PricingStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PricingFactory {

    private static final Map<Character, Integer> SKU_PRICES = new HashMap<>();
    private static final Map<Character, Discount> SKU_DISCOUNTS = new HashMap<>();
    private static final Map<Integer, SpecialOffer> SKU_SPECIAL_OFFERS = new HashMap<>();

    static {
        SKU_PRICES.put('A', 50);
        SKU_PRICES.put('B', 30);
        SKU_PRICES.put('C', 20);
        SKU_PRICES.put('D', 15);
        SKU_PRICES.put('E', 40);

        SKU_DISCOUNTS.put('A', new Discount(3, 130));
        SKU_DISCOUNTS.put('B', new Discount(2, 45));

        SpecialOffer specialOffer = new SpecialOffer();
        specialOffer.addOffer('A', 3, 130);
        specialOffer.addOffer('A', 5, 200);
        specialOffer.addOffer('B', 2, 45);
        specialOffer.addOffer('E', 2, 0);


        SKU_SPECIAL_OFFERS.put(1, specialOffer);
    }

    public PricingStrategy getStrategy(char sku) {
        Integer price = SKU_PRICES.get(sku);
        Discount discount = SKU_DISCOUNTS.get(sku);

        SKU_SPECIAL_OFFERS.get('A');

        for (SpecialOffer specialOffer : SKU_SPECIAL_OFFERS.values()) {

        }

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


package befaster.solutions.common.factory;

import befaster.solutions.common.dto.Discount;
import befaster.solutions.common.dto.SpecialOffer;
import befaster.solutions.common.strategy.DefaultPricingStrategy;
import befaster.solutions.common.strategy.PricingStrategy;
import befaster.solutions.common.strategy.SpecialOfferPricingStrategy;

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

        SpecialOffer specialOfferA = new SpecialOffer();
        specialOfferA.addOffer('A', 3, 130);
        specialOfferA.addOffer('A', 5, 200);
        SKU_SPECIAL_OFFERS.put('A', specialOfferA);

        SpecialOffer specialOfferB = new SpecialOffer();
        specialOfferB.addOffer('B', 2, 45);
        SKU_SPECIAL_OFFERS.put('B', specialOfferB);

        SpecialOffer specialOfferE = new SpecialOffer();
        specialOfferE.addOffer('E', 2, 80, 'B', 30);
        SKU_SPECIAL_OFFERS.put('E', specialOfferE);
    }

    public PricingStrategy getStrategy(char sku) {
        Integer price = SKU_PRICES.get(sku);
        SpecialOffer specialOffer = SKU_SPECIAL_OFFERS.get(sku);

        if(specialOffer != null) {
            return new SpecialOfferPricingStrategy(specialOffer,  price);
        } else {
            return new DefaultPricingStrategy(price);
        }
    }

    public Map<Character, Integer> getSKUPrices(char sku) {
        return new HashMap<>(SKU_PRICES);
    }

    public boolean isValidSku(char sku) {
        return SKU_PRICES.containsKey(sku);
    }
}


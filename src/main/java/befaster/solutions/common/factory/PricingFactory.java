package befaster.solutions.common.factory;

import befaster.solutions.common.dto.GroupDiscount;
import befaster.solutions.common.dto.Offer;
import befaster.solutions.common.dto.Promotion;
import befaster.solutions.common.pricing.config.PricingConfig;

import java.util.List;
import java.util.Map;

public class PricingFactory {

    public static final Map<Character, Integer> SKU_PRICES;
    public static final Map<Character, List<Offer>> SKU_OFFERS;

    public static final Map<Character, Promotion> SKU_PROMOTIONS;
    public static final GroupDiscount GROUP_DISCOUNTS;


    private PricingFactory() {
    }


    static {
    }

    private static PricingConfig loadConfig() {
        Object
    }

    public boolean isValidSku(char sku) {
        return SKU_PRICES.containsKey(sku);
    }
}


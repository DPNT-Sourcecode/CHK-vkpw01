package befaster.solutions.common.factory;

import befaster.solutions.common.dto.Offer;
import befaster.solutions.common.dto.Promotion;

import java.util.HashMap;
import java.util.Map;

public class PricingFactory {

    public static final Map<Character, Integer> SKU_PRICES = new HashMap<>();
    public static final Map<Character, Offer[]> SKU_OFFERS = new HashMap<>();

    public static final Map<Character, Promotion> SKU_PROMOTIONS = new HashMap<>();

    static {
        SKU_PRICES.put('A', 50);
        SKU_PRICES.put('B', 30);
        SKU_PRICES.put('C', 20);
        SKU_PRICES.put('D', 15);
        SKU_PRICES.put('E', 40);

        SKU_OFFERS.put('A', new Offer[]{new Offer(5,200), new Offer(3, 130)});

        SKU_PROMOTIONS.put('E', new Promotion(2,'B', 1));
    }

    public boolean isValidSku(char sku) {
        return SKU_PRICES.containsKey(sku);
    }
}

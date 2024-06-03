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
        SKU_PRICES.put('F', 10);
        SKU_PRICES.put('G', 20);
        SKU_PRICES.put('H', 10);
        SKU_PRICES.put('I', 35);
        SKU_PRICES.put('J', 60);
        SKU_PRICES.put('K', 80);
        SKU_PRICES.put('L', 90);
        SKU_PRICES.put('M', 15);
        SKU_PRICES.put('N', 40);
        SKU_PRICES.put('O', 10);
        SKU_PRICES.put('P', 50);
        SKU_PRICES.put('Q', 30);
        SKU_PRICES.put('R', 50);
        SKU_PRICES.put('S', 30);
        SKU_PRICES.put('T', 20);
        SKU_PRICES.put('U', 40);
        SKU_PRICES.put('V', 50);
        SKU_PRICES.put('W', 20);
        SKU_PRICES.put('X', 90);
        SKU_PRICES.put('Y', 10);
        SKU_PRICES.put('Z', 50);

        SKU_OFFERS.put('A', new Offer[]{new Offer(5,200), new Offer(3, 130)});
        SKU_OFFERS.put('B', new Offer[]{new Offer(2,45)});
        SKU_OFFERS.put('H', new Offer[]{new Offer(5,45), new Offer(10, 80)});
        SKU_OFFERS.put('K', new Offer[]{new Offer(2,150)});
        SKU_OFFERS.put('P', new Offer[]{new Offer(5,200)});
        SKU_OFFERS.put('Q', new Offer[]{new Offer(3,80)});
        SKU_OFFERS.put('V', new Offer[]{new Offer(2,90), new Offer(3, 130)});


        SKU_PROMOTIONS.put('E', new Promotion(2,'B', 1));
        SKU_PROMOTIONS.put('F', new Promotion(3,'F', 1));
        SKU_PROMOTIONS.put('N', new Promotion(3,'M', 1));
        SKU_PROMOTIONS.put('R', new Promotion(3,'Q', 1));
        SKU_PROMOTIONS.put('U', new Promotion(4,'U', 1));
    }

    public boolean isValidSku(char sku) {
        return SKU_PRICES.containsKey(sku);
    }
}

package befaster.solutions.common.factory;

import befaster.solutions.common.dto.GroupDiscount;
import befaster.solutions.common.dto.Offer;
import befaster.solutions.common.dto.Promotion;
import befaster.solutions.common.pricing.config.PricingConfig;
import befaster.solutions.common.pricing.strategy.OfferPricingStrategy;
import befaster.solutions.common.pricing.strategy.PricingStrategy;
import befaster.solutions.common.pricing.strategy.RegularPricingStrategy;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PricingFactory {

    private static final PricingFactory INSTANCE = new PricingFactory();
    public static final Map<Character, Integer> SKU_PRICES;
    public static final Map<Character, List<Offer>> SKU_OFFERS;

    public static final Map<Character, Promotion> SKU_PROMOTIONS;
    public static final GroupDiscount GROUP_DISCOUNTS;


    private PricingFactory() {
    }

    static {
        PricingConfig config = loadConfig();
        SKU_PRICES = Collections.unmodifiableMap(config.prices());
        SKU_OFFERS = Collections.unmodifiableMap(config.offers());
        SKU_PROMOTIONS = Collections.unmodifiableMap(config.promotions());
        GROUP_DISCOUNTS = config.groupDiscounts();
    }

    public static PricingFactory getInstance() {
        return INSTANCE;
    }

    private static PricingConfig loadConfig() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            return mapper.readValue(new File("C:\\Projetos\\runner-for-java-windows\\accelerate_runner\\src\\main\\resources\\pricing.json"),
                    PricingConfig.class);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load pricing json config file", e);
        }
    }

    public PricingStrategy getStrategy(char sku) {
        Integer price = SKU_PRICES.get(sku);
        List<Offer> offers = SKU_OFFERS.get(sku);

        if(offers != null) {
            return new OfferPricingStrategy(price, offers);
        } else {
            return new RegularPricingStrategy(price);
        }
    }

    public boolean isValidSku(char sku) {
        return SKU_PRICES.containsKey(sku);
    }
}


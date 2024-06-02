package befaster.solutions.common.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record SpecialOffer(Map<Character, List<Offer>> offers) {

    public SpecialOffer() {
        this(new HashMap<>());
    }

    public void addOffer(char sku, int quantity, int price, char freeItem) {
        offers.computeIfAbsent(sku, k -> new ArrayList<>()).add(new Offer(quantity, price, freeItem));
    }

    public List<Offer> getOffers(char sku) {
        return offers.getOrDefault(sku, new ArrayList<>());
    }

    public record Offer(int quantity, int price, char freeItem) {
    }
}

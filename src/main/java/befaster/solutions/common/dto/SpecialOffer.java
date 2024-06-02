package befaster.solutions.common.dto;

import java.util.ArrayList;
import java.util.List;

public class SpecialOffer {

    private final List<Offer> offers;

    public SpecialOffer() {
        this.offers = new ArrayList<>();
    }

    public void addOffer(char sku, int quantity, int price) {
        offers.add(new Offer(sku, quantity, price));
    }

    public boolean containsOfferWithSku(char sku) {
        for (Offer offer : offers) {
            if (offer.sku == sku) {
                return true;
            }
        }
        return false;
    }

    private static class Offer {

        private final char sku;
        private final int quantity;
        private final int price;
        public Offer(char sku, int quantity, int price) {
            this.sku = sku;
            this.quantity = quantity;
            this.price = price;
        }
    }
}



package befaster.solutions.common.dto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SpecialOffer {

    private final List<Offer> offers;

    public SpecialOffer() {
        this.offers = new ArrayList<>();
    }

    public void addOffer(char sku, int quantity, int price) {
        offers.add(new Offer(sku, quantity, price));
    }

    public Offer getBestOffer(char sku) {
        List<Offer> result = new ArrayList<>();

        for (Offer offer : offers) {
            if (offer.sku == sku) {
                result.add(offer);
            }
        }
        return compareBestOffer(result);
    }

    private Offer compareBestOffer(List<Offer> offers) {

        return offers.stream()
                .min(Comparator.comparing(Offer::getPrice))
                .orElse(null);
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


        public int getQuantity() {
            return quantity;
        }

        public int getPrice() {
            return price;
        }

    }

}

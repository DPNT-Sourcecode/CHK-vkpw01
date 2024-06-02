package befaster.solutions.common.dto;

import java.util.*;

public class SpecialOffer {

    private final List<Offer> offers;

    public SpecialOffer() {
        this.offers = new ArrayList<>();
    }

    public void addOffer(char sku, int quantity, int price) {
        offers.add(new Offer(sku, quantity, price));
    }

    public void addOffer(char sku, int quantity, int price, char freeItemSku) {
        offers.add(new Offer(sku, quantity, price, freeItemSku));
    }

    public List<Offer> getOffersWithSku(char sku) {
        List<Offer> result = new ArrayList<>();

        for (Offer offer : offers) {
            if (offer.sku == sku) {
                result.add(offer);
            }
        }
        return result;
    }

    public int getPriceForSku(char sku) {
        for (Offer offer : offers) {
            if (offer.getSku() == sku) {
                return offer.getPrice();
            }
        }

        return 0;
    }

    public Offer getBestOfferWithSku(char sku, int quantity) {

        List<SpecialOffer.Offer> offerWithSku = getOffersWithSku(sku);

        if (offerWithSku.isEmpty()) {
            return null;
        }

        SpecialOffer.Offer bestOffer = null;

        for (SpecialOffer.Offer offer : offerWithSku) {
            if (quantity >= offer.getQuantity()) {
                bestOffer = offer;
            }
        }

        return bestOffer;
    }

    public Set<Character> getSKUs() {

        Set<Character> skus = new HashSet<>();

        for (Offer offer : offers) {
            skus.add(offer.getSku());
            if (offer.getFreeItemSku() != ' ') {
                skus.add(offer.getFreeItemSku());
            }
        }
        return skus;
    }

    public boolean containsSku(char sku) {
        for (Offer offer : offers) {
            if (offer.getSku() == sku || offer.getFreeItemSku() == sku) {
                return true;
            }
        }
        return false;
    }

    public static class Offer {

        private final char sku;
        private final int quantity;
        private final int price;
        private final char freeItemSku;

        public Offer(char sku, int quantity, int price, char freeItemSku) {
            this.sku = sku;
            this.quantity = quantity;
            this.price = price;
            this.freeItemSku = freeItemSku;
        }

        public Offer(char sku, int quantity, int price) {
            this(sku, quantity, price, ' ');
        }

        public int getQuantity() {
            return quantity;
        }

        public Character getSku() {
            return sku;
        }

        public int getPrice() {
            return price;
        }

        public Character getFreeItemSku() {
            return freeItemSku;
        }

    }

}

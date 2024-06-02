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

    public void addOffer(char sku, int quantity, int price, char freeItemSku, int freeItemSkuPrice) {
        offers.add(new Offer(sku, quantity, price, freeItemSku, freeItemSkuPrice));
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
                return offer.getFreeItemSkuPrice();
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
        private final int freeItemSkuPrice;

        public Offer(char sku, int quantity, int price, char freeItemSku, int freeItemSkuPrice) {
            this.sku = sku;
            this.quantity = quantity;
            this.price = price;
            this.freeItemSku = freeItemSku;
            this.freeItemSkuPrice = freeItemSkuPrice;
        }

        public Offer(char sku, int quantity, int price) {
            this(sku, quantity, price, ' ', 0);
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

        public int getFreeItemSkuPrice() {
            return freeItemSkuPrice;
        }

    }

}


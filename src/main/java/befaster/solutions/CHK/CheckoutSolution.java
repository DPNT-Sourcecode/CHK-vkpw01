package befaster.solutions.CHK;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSolution {
    public Integer checkout(String skus) {
        if(skus == null || skus.isEmpty()) {
            return -1;
        }

        Map<Character, Integer> countOfItems = new HashMap<>();
        for(char sku : skus.toCharArray()) {
            countOfItems.put(sku, countOfItems.getOrDefault(sku, 0) + 1);
        }

        int totalValue = 0;

        for(Map.Entry<Character, Integer> entry : countOfItems.entrySet()) {
            char sku = entry.getKey();
            int count = entry.getValue();


        }

        return totalValue;
    }
}


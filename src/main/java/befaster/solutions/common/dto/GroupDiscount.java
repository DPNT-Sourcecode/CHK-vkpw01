package befaster.solutions.common.dto;

import java.util.Set;

public record GroupDiscount(Set<Character> items, int requiredQuantity, int discountPrice) {
}

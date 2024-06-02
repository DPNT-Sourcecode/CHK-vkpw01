package befaster.solutions.CHK;

import befaster.solutions.common.factory.PricingFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp() {
        PricingFactory pricingFactory = new PricingFactory();
        checkoutSolution = new CheckoutSolution(pricingFactory);
    }

    @Test
    public void testCheckoutEmptyString() {
        assertEquals(-1, checkoutSolution.checkout(""));
    }

    @Test
    public void testCheckoutWithUniqueItems() {
        assertEquals(50, checkoutSolution.checkout("A"));
        assertEquals(30, checkoutSolution.checkout("B"));
        assertEquals(20, checkoutSolution.checkout("C"));
        assertEquals(15, checkoutSolution.checkout("D"));
    }

    @Test
    public void testCheckoutWithDiscounts() {
        assertEquals(130, checkoutSolution.checkout("AAA"));
    }

    @Test
    public void testNotCapitalized() {
        assertEquals(50, checkoutSolution.checkout("a"));
    }

}
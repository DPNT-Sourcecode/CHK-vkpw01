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

}
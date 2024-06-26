package befaster.solutions.CHK;

import befaster.solutions.common.factory.PricingFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutSolutionTest {

    private CheckoutSolution checkoutSolution;

    @BeforeEach
    public void setUp() {
        PricingFactory pricingFactory = PricingFactory.getInstance();
        checkoutSolution = new CheckoutSolution(pricingFactory);
    }

    @Test
    public void testCheckoutEmptyString() {
        assertEquals(0, checkoutSolution.checkout(""));
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
        assertEquals(130, checkoutSolution.checkout("AAA"));
    }

    @Test
    public void testIllegalCharacters() {
        assertEquals(0, checkoutSolution.checkout(""));
        assertEquals(-1, checkoutSolution.checkout("-"));
        assertEquals(-1, checkoutSolution.checkout("a"));
        assertEquals(-1, checkoutSolution.checkout("AaXa"));
        assertEquals(50, checkoutSolution.checkout("A"));
    }

    @Test
    public void testSpecialOffer() {
        assertEquals(180, checkoutSolution.checkout("AAAA"));
        assertEquals(200, checkoutSolution.checkout("AAAAA"));
        assertEquals(250, checkoutSolution.checkout("AAAAAA"));
        assertEquals(300, checkoutSolution.checkout("AAAAAAA"));
        assertEquals(330, checkoutSolution.checkout("AAAAAAAA"));
        assertEquals(380, checkoutSolution.checkout("AAAAAAAAA"));
        assertEquals(80, checkoutSolution.checkout("EE"));
        assertEquals(80, checkoutSolution.checkout("EEB"));
        assertEquals(120, checkoutSolution.checkout("EEEB"));
        assertEquals(160, checkoutSolution.checkout("EEEEBB"));
    }

    @Test
    public void testMultiPricing() {
        assertEquals(20, checkoutSolution.checkout("FF"));
        assertEquals(30, checkoutSolution.checkout("FFFF"));
        assertEquals(40, checkoutSolution.checkout("FFFFFF"));
        assertEquals(80, checkoutSolution.checkout("HHHHHHHHHH"));
        assertEquals(90, checkoutSolution.checkout("HHHHHHHHHHH"));
        assertEquals(100, checkoutSolution.checkout("HHHHHHHHHHHH"));
        assertEquals(125, checkoutSolution.checkout("HHHHHHHHHHHHHHH"));
    }

    @Test
    public void testAnyPricing() {
        assertEquals(45, checkoutSolution.checkout("STX"));
        assertEquals(90, checkoutSolution.checkout("STXSTX"));
        assertEquals(65, checkoutSolution.checkout("SSSZ"));
        assertEquals(75, checkoutSolution.checkout("SSSB"));
        assertEquals(315, checkoutSolution.checkout("SXTSSSTXSAAAA"));
        assertEquals(62, checkoutSolution.checkout("STXZ"));
        assertEquals(1602, checkoutSolution.checkout("ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ"));
    }
}
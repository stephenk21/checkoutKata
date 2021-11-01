package cart;

import application.cart.CartOperations;
import application.config.FileReaderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartOperationsTests extends CartTestParameters {
    CartOperations cart;

    @BeforeEach
    void newCart() throws FileReaderException {
        cart = new CartOperations();
    }

    @Test
    void addItemsToCart() {
        cart.addItemsToCart("A", 2);
        cart.addItemsToCart("D");

        assertEquals(2, cart.getBasket().size());
        assertEquals(2, cart.getBasket().get("A"));
        assertEquals(1, cart.getBasket().get("D"));
    }

    @Test
    void addInvalidItemToCart() {
        cart.addItemsToCart("E");
        assertEquals(0, cart.getBasket().size());
    }

    @Test
    void removeItemsFromCart() {
        cart.addItemsToCart("A", 2);
        cart.addItemsToCart("B");
        cart.removeItemsFromCart("A", 3);


        assertEquals(1, cart.getBasket().size());
    }

    @Test
    void removeInvalidItemsFromCart() {
        cart.removeItemsFromCart("E", 1);
        cart.removeItemsFromCart("C", 3);

        assertEquals(0, cart.getBasket().size());
    }

    @ParameterizedTest
    @MethodSource("cartItemsToAdd")
    void totalTest(int A, int B, int C, int D, int total) {
        cart.addItemsToCart("A", A);
        cart.addItemsToCart("B", B);
        cart.addItemsToCart("C", C);
        cart.addItemsToCart("D", D);

        cart.calculateTotalPrice();
        assertEquals(total, cart.getTotal());
    }

}

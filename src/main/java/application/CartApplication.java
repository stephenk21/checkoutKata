package application;

import application.cart.CartConsole;
import application.config.FileReaderException;

public class CartApplication {
    public static void main(String[] args) throws FileReaderException {
        new CartConsole();
    }
}

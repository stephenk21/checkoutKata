package application.cart;

import application.config.FileReaderException;

import java.util.Scanner;

import static java.lang.Math.abs;

public class CartConsole {
    private CartOperations cart = new CartOperations();
    private int ch;

    public CartConsole() throws FileReaderException {
        menu();
    }

    public void startScreen() {
        System.out.println("1. Display and Buy Store Products");
        System.out.println("2. Display Cart and Total Price");
        System.out.println("3. Clear cart");
        System.out.println("0. Exit");
    }

    public void menu() throws FileReaderException {
        do {
            startScreen();
            getUserIntegerInput();

            switch (ch) {
                case 1:
                    displayStoreProducts();
                    addProductToCart();
                    break;
                case 2:
                    showCart();
                    break;
                case 3:
                    resetCart();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (ch != 0);
    }


    private int getUserIntegerInput() throws NumberFormatException {
        Scanner in = new Scanner(System.in);
        boolean doNotProceed = true;
        while (doNotProceed) {
            try {
                ch = Integer.parseInt(in.nextLine());
                doNotProceed = false;
            } catch (NumberFormatException e) {
                System.out.println("Please input a valid number");
            }
        }
        return ch;
    }

    private String getUserStringInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }


    private void displayStoreProducts() {
        System.out.println("Here are the products");
        cart.getItemList().getItemMap().forEach((key, value) -> System.out.println(
                key
        ));
    }

    private void addProductToCart() {
        System.out.println("Please input your item");
        String inputString = getUserStringInput();
        System.out.println("Please select your quantity. To remove items input a negative number");
        int quantity = getUserIntegerInput();
        if (quantity > 0) {
            cart.addItemsToCart(inputString, abs(quantity));
        } else {
            cart.removeItemsFromCart(inputString, abs(quantity));
        }
    }

    private void showCart() {
        cart.getBasket().forEach((key, value) -> System.out.println(
                key + " " + value
        ));
        cart.calculateTotalPrice();
        System.out.printf("The total price is: %s%n", cart.getTotal());
    }

    //In the event the file has been modified this will read the file again
    public void resetCart() throws FileReaderException {
        cart = new CartOperations();
    }
}

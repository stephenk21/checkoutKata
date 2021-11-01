package application.cart;

import application.config.FileReader;
import application.config.FileReaderException;
import application.items.ItemList;
import application.offers.Offer;
import application.offers.OfferList;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.floor;

public class CartOperations {
    @Getter
    private final ItemList itemList;
    @Getter
    private final OfferList offerList;
    @Getter
    private final Map<String, Integer> basket;
    private final Logger logger;
    @Getter
    private double total;

    public CartOperations() throws FileReaderException {
        FileReader fileReader = new FileReader();
        itemList = fileReader.generateItems();
        offerList = fileReader.generateOffers();
        basket = new HashMap<>();
        logger = LoggerFactory.getLogger(CartOperations.class);
        logger.info("Items and Offers successfully loaded");
    }

    public void addItemsToCart(String item) {
        addItemsToCart(item, 1);
    }

    // Adds items to the cart
    public void addItemsToCart(String item, int quantity) {
        if (itemList.get(item) != null && quantity > 0) {
            basket.computeIfPresent(item, (k, v) -> v + quantity);
            basket.putIfAbsent(item, quantity);
        } else {
            logger.warn("Invalid input for item with name: {} and quantity {}", item, quantity);
        }
    }

    // Remove items from the cart. If the quantity goes to 0 or less remove the item from the cart entirely.
    public void removeItemsFromCart(String item, int quantity) {
        if (itemList.get(item) != null) {
            basket.computeIfPresent(item, (k, v) -> v - quantity);
            basket.entrySet().removeIf(entry -> entry.getValue() <= 0);
        } else {
            logger.warn("No item present with name: {}", item);
        }
    }

    // Calculate the total price of items in the cart using item prices and offers
    public void calculateTotalPrice() {
        total = 0;
        for (Map.Entry<String, Integer> entry : basket.entrySet()) {
            Offer offer = offerList.get(entry.getKey());
            int totalItems = entry.getValue();
            double itemPrice = itemList.get(entry.getKey()).getPrice();
            int offerQuantity;
            double offerPrice;

            if (offer != null) {
                offerQuantity = offer.getQuantity();
                offerPrice = offer.getPrice();
            } else {
                offerQuantity = 0;
                offerPrice = 0;
            }

            if (totalItems >= offerQuantity && offerQuantity > 0) {
                // Total price for number of times we can get a special offer
                total += (floor(totalItems / (double) offerQuantity)) * offerPrice;
                // Total price for remaining items that we can't get an offer for.
                total += (totalItems % offerQuantity) * itemPrice;
            } else {
                // Total price for items without any special offer
                total += totalItems * itemPrice;
            }
        }
    }

}

package application.offers;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Offer {
    @Getter
    private String name;
    @Getter
    private int quantity;
    @Getter
    private double price;
}

package application.items;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Item {
    @Getter
    private String name;
    @Getter
    private double price;
}

package application.items;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class ItemList {
    @Getter
    private final Map<String, Item> itemMap = new HashMap<>();

    public void add(Item i) {
        if (get(i.getName()) == null) {
            itemMap.put(i.getName(), i);
        } else {
            throw new IllegalArgumentException("Attempt to add duplicate item was detected");
        }
    }

    public Item get(String name) {
        return itemMap.get(name);
    }
}

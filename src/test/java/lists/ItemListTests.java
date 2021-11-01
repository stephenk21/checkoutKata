package lists;

import application.items.Item;
import application.items.ItemList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemListTests {

    final Item testItem = new Item("A", 1);
    final Item testItem2 = new Item("B", 5);
    ItemList itemList;

    @BeforeEach
    void newMap() {
        itemList = new ItemList();
    }

    @Test
    void addItem() {
        itemList.add(testItem);

        assertEquals(1, itemList.getItemMap().size());
    }

    @Test
    void addDuplicateItem() {
        itemList.add(testItem);
        assertThrows(IllegalArgumentException.class, () -> itemList.add(testItem));
    }

    @Test
    void getItem() {
        itemList.add(testItem);
        itemList.add(testItem2);
        Item i = itemList.get("A");

        assertEquals(i, testItem);
    }

    @Test
    void getInvalidItem() {
        Item i = itemList.get("A");

        assertNull(i);
    }

}

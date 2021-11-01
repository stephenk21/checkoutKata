package config;

import application.config.FileReader;
import application.config.FileReaderException;
import application.items.ItemList;
import application.offers.OfferList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FileReaderTests {
    FileReader fileReader;

    @Test
    void generateItemList() throws FileReaderException {
        fileReader = new FileReader();
        ItemList itemList = fileReader.generateItems();

        assertEquals(4, itemList.getItemMap().size());
    }

    @Test
    void generateOfferList() throws FileReaderException {
        fileReader = new FileReader();
        OfferList offerList = fileReader.generateOffers();

        assertEquals(2, offerList.getOfferMap().size());
    }

    @Test
    void invalidFiles() throws FileReaderException {
        fileReader = new FileReader();
        fileReader.loadConfig("BadConfig.json");

        assertThrows(FileReaderException.class, () -> fileReader.generateItems());
        assertThrows(FileReaderException.class, () -> fileReader.generateOffers());
    }
}

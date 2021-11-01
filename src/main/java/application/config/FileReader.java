package application.config;

import application.items.Item;
import application.items.ItemList;
import application.offers.Offer;
import application.offers.OfferList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;


public class FileReader {
    private static final String CONFIG_PATH = "Config.json";
    private String itemPath;
    private String offerPath;
    private String itemSKU;
    private String itemPrice;
    private String offerQuantity;
    private String offerPrice;

    public FileReader() throws FileReaderException {
        loadConfig((CONFIG_PATH));
    }

    public ItemList generateItems() throws FileReaderException {
        ItemList itemList = new ItemList();
        try {
            for (JsonNode j : loadJsonFile(itemPath)) {
                itemList.add(new Item(j.get(itemSKU).asText(), j.get(itemPrice).asDouble()));
            }
        } catch (IllegalArgumentException e) {
            throw new FileReaderException("Error adding items, please check the content of your items file");
        }
        return itemList;
    }

    public OfferList generateOffers() throws FileReaderException {
        OfferList offerList = new OfferList();
        try {
            for (JsonNode j : loadJsonFile(offerPath)) {
                offerList.add(new Offer(j.get(itemSKU).asText(), j.get(offerQuantity).asInt(), j.get(offerPrice).asDouble()));
            }
        } catch (IllegalArgumentException e) {
            throw new FileReaderException("Error adding offers, please check the content of your offers file");
        }
        return offerList;
    }

    JsonNode loadJsonFile(String filename) throws FileReaderException {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(filename)) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(in,
                    JsonNode.class);
        } catch (IOException e) {
            throw new FileReaderException(String.format("Error loading %s", filename));
        }
    }

    public void loadConfig(String configPath) throws FileReaderException {
        JsonNode jsonNode = loadJsonFile(configPath);
        itemPath = jsonNode.get("ItemPath").asText();
        offerPath = jsonNode.get("OfferPath").asText();
        itemSKU = jsonNode.get("ItemSKU").asText();
        itemPrice = jsonNode.get("ItemPrice").asText();
        offerQuantity = jsonNode.get("OfferQuantity").asText();
        offerPrice = jsonNode.get("OfferPrice").asText();
    }
}

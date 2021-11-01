package application.offers;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class OfferList {
    @Getter
    private final Map<String, Offer> offerMap = new HashMap<>();

    public void add(Offer o) {
        offerMap.put(o.getName(), o);
    }

    public Offer get(String name) {
        return offerMap.get(name);
    }
}

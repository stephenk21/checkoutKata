package lists;

import application.offers.Offer;
import application.offers.OfferList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OfferListTests {

    final Offer testOffer = new Offer("A", 2, 10);
    final Offer testItem2 = new Offer("B", 5, 30);
    OfferList offerList;

    @BeforeEach
    void newMap() {
        offerList = new OfferList();
    }

    @Test
    void addOffer() {
        offerList.add(testOffer);

        assertEquals(1, offerList.getOfferMap().size());
    }

    @Test
    void getOffer() {
        offerList.add(testOffer);
        offerList.add(testItem2);
        Offer o = offerList.get("A");

        assertEquals(o, testOffer);
    }

    @Test
    void getInvalidOffer() {
        Offer o = offerList.get("A");
        assertNull(o);
    }

}

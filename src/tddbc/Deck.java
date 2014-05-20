package tddbc;

import tddbc.Card.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by takatama on 2014/05/17.
 */
public class Deck {
    List<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        init();
    }

    public Deck init() {
        for(Suit suit: Suit.values()) {
            for (int i = 0; i < 13; i++) {
                cards.add(CardFactory.create(i + 1));
            }
        }
        return this;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card pull() {
        return cards.remove(0);
    }
}

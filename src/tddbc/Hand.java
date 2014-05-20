package tddbc;

import tddbc.card.AceCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by takatama on 2014/05/17.
 */
public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }

    public Hand add(Card card) {
        cards.add(card);
        return this;
    }


    private int countAces() {
        int aces = 0;
        for (Card card: cards) {
            if (card instanceof AceCard) {
                aces += 1;
            }
        }
        return aces;
    }

    public int score() {
        int total = 0;

        for (Card card: cards) {
            total += card.score();
        }

        for (int i = 0, aces = countAces(); i < aces; i++) {
            if (total > 21) {
                total -= 10;
            }
        }

        return total;
    }

    public boolean isBlackJack() {
        return cards.size() == 2 && countAces() == 1 && score() == 21;
    }
}

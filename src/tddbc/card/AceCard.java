package tddbc.card;

import tddbc.Card;

/**
 * Created by takatama on 2014/05/17.
 */
public class AceCard implements Card {
    private final int value;

    private AceCard(int value) {
        assert value == 1;
        this.value = value;
    }

    public static AceCard valueOf(int value) {
        return new AceCard(value);
    }

    public int score() {
        return 11;
    }
}

package tddbc.card;

import tddbc.Card;

/**
 * Created by takatama on 2014/05/17.
 */
public class PipCard implements Card {
    private final int value;

    private PipCard(int value) {
        assert 0 < value && value < 11;
        this.value = value;
    }

    public static PipCard valueOf(int value) {
        return new PipCard(value);
    }

    public int score() {
        return value;
    }
}

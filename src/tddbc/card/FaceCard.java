package tddbc.card;

import tddbc.Card;

/**
 * Created by takatama on 2014/05/17.
 */
public class FaceCard implements Card {
    private final int value;

    private FaceCard(int value) {
        assert 10 < value && value < 14;
        this.value = value;
    }

    public static FaceCard valueOf(int value) {
        return new FaceCard(value);
    }

    public int score() {
        return 10;
    }
}

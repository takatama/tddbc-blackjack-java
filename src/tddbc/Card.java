package tddbc;

/**
 * Created by takatama on 2014/05/17.
 */
public interface Card {
    enum Suit {SPADE, HEART, DIAMOND, CLUB};

    public int score();
}

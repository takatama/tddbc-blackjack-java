package tddbc;

/**
 * Created by takatama on 2014/05/17.
 */
public class Player {
    Deck deck;
    Hand hand;

    public enum Result {WIN, DRAW, LOST}

    public Player(Deck deck) {
        this.deck = deck;
        init();
    }

    public Player init() {
        this.hand = new Hand();
        return this;
    }

    public Player hit() {
        hand.add(deck.pull());
        return this;
    }

    public boolean isBust() {
        return hand.score() > 21;
    }

    public int score() {
        return hand.score();
    }

    public Result match(Player opponent) {
        if (this.isBust() && opponent.isBust()) {
            return Result.DRAW;
        }
        if (this.isBust() && !opponent.isBust()) {
            return Result.LOST;
        }
        if (opponent.isBust()) {
            return Result.WIN;
        }
        if (this.score() == opponent.score()) {
            if (this.isBlackJack() && opponent.isBlackJack()) {
                return Result.DRAW;
            }
            if (!this.isBlackJack() && opponent.isBlackJack()) {
                return Result.LOST;
            }
            if (this.isBlackJack() && !opponent.isBlackJack()) {
                return Result.WIN;
            }
            return Result.DRAW;
        }
        if (this.score() < opponent.score()) {
            return Result.LOST;
        }
        return Result.WIN;
    }

    public boolean isBlackJack() {
        return hand.isBlackJack();
    }
}

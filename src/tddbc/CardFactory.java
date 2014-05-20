package tddbc;

import tddbc.card.AceCard;
import tddbc.card.FaceCard;
import tddbc.card.PipCard;

/**
 * Created by takatama on 2014/05/17.
 */
public class CardFactory {
    public static Card create(int value) {
        if (value > 10) {
            return FaceCard.valueOf(value);
        }
        if (value == 1) {
            return AceCard.valueOf(value);
        }
        return PipCard.valueOf(value);
    }
}

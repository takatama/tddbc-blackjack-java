package tddbc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PlayerTest {
    Deck deck;
    Player player;
    Player opponent;

    @Before
    public void setUp() {
        deck = new Deck();
        player = new Player(deck);
        opponent = new Player(deck);
    }

    @Test
    public void シャッフルしていないデッキから6回まで引ける() {
        player.hit().hit().hit().hit().hit().hit();
        assertThat(player.score(), is(21));
        assertThat(player.isBust(), is(false));
        player.hit();
        assertThat(player.score(), is(28));
        assertThat(player.isBust(), is(true));
    }

    private Card card(int value) {
        return CardFactory.create(value);
    }

    @Test
    public void 自分がバースト_相手もバーストだと引き分け() {
        player.hand.add(card(10)).add(card(10)).add(card(10));
        opponent.hand.add(card(10)).add(card(10)).add(card(10));
        assertThat(player.isBust(), is(true));
        assertThat(opponent.isBust(), is(true));
        assertThat(player.match(opponent), is(Player.Result.DRAW));
    }

    @Test
    public void 自分がバースト_相手はバーストしていないと負け() {
        player.hand.add(card(10)).add(card(10)).add(card(10));
        opponent.hand.add(card(10)).add(card(10));
        assertThat(player.isBust(), is(true));
        assertThat(opponent.isBust(), is(false));
        assertThat(player.match(opponent), is(Player.Result.LOST));
    }

    @Test
    public void 自分がバーストしていない_相手はバーストしていると勝ち() {
        player.hand.add(card(10)).add(card(10));
        opponent.hand.add(card(10)).add(card(10)).add(card(10));
        assertThat(player.isBust(), is(false));
        assertThat(opponent.isBust(), is(true));
        assertThat(player.match(opponent), is(Player.Result.WIN));
    }

    @Test
    public void 自分がバーストしていない_相手がバーストしていない_スコアが同じだと引き分け() {
        player.hand.add(card(10)).add(card(10));
        opponent.hand.add(card(10)).add(card(10));
        assertThat(player.isBust(), is(false));
        assertThat(opponent.isBust(), is(false));
        assertThat(player.match(opponent), is(Player.Result.DRAW));
    }

    @Test
    public void 自分がバーストしていない_相手がバーストしていない_スコアが低いと負け() {
        player.hand.add(card(10)).add(card(10));
        opponent.hand.add(card(10)).add(card(1));
        assertThat(player.isBust(), is(false));
        assertThat(opponent.isBust(), is(false));
        assertThat(player.match(opponent), is(Player.Result.LOST));
    }

    @Test
    public void 自分がバーストしていない_相手がバーストしていない_スコアが高いと勝ち() {
        player.hand.add(card(10)).add(card(1));
        opponent.hand.add(card(10)).add(card(10));
        assertThat(player.isBust(), is(false));
        assertThat(opponent.isBust(), is(false));
        assertThat(player.match(opponent), is(Player.Result.WIN));
    }

    @Test
    public void Aと10でブラックジャック() {
        player.hand.add(card(1)).add(card(10));
        assertThat(player.isBlackJack(), is(true));
    }

    @Test
    public void Aと13でブラックジャック() {
        player.hand.add(card(1)).add(card(13));
        assertThat(player.isBlackJack(), is(true));
    }

    @Test
    public void 自分がブラックジャックで相手がブラックジャックなら引き分け() {
        player.hand.add(card(1)).add(card(13));
        opponent.hand.add(card(1)).add(card(13));
        assertThat(player.isBlackJack(), is(true));
        assertThat(opponent.isBlackJack(), is(true));
        assertThat(player.match(opponent), is(Player.Result.DRAW));
    }

    @Test
    public void 得点が21でも自分だけがブラックジャックなら勝ち() {
        player.hand.add(card(1)).add(card(13));
        opponent.hand.add(card(10)).add(card(10)).add(card(1));
        assertThat(player.isBlackJack(), is(true));
        assertThat(opponent.isBlackJack(), is(false));
        assertThat(player.match(opponent), is(Player.Result.WIN));
    }

    @Test
    public void 得点が21でも相手だけがブラックジャックなら負け() {
        player.hand.add(card(10)).add(card(10)).add(card(1));
        opponent.hand.add(card(1)).add(card(13));
        assertThat(player.isBlackJack(), is(false));
        assertThat(opponent.isBlackJack(), is(true));
        assertThat(player.match(opponent), is(Player.Result.LOST));
    }

    @Test
    public void 自分が19_相手が22なら勝ち() {
        player.hand.add(card(10)).add(card(9));
        opponent.hand.add(card(10)).add(card(10)).add(card(2));
        assertThat(player.score(), is(19));
        assertThat(player.isBust(), is(false));
        assertThat(opponent.score(), is(22));
        assertThat(opponent.isBust(), is(true));
        assertThat(player.match(opponent), is(Player.Result.WIN));
    }
}
package tddbc;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HandTest {
    Hand hand;

    @Before
    public void Setup() {
        hand = new Hand();
    }

    void add(int value) {
        hand.add(CardFactory.create(value));
    }

    @Test
    public void カードが2なら得点は2() {
        add(2);
        assertThat(hand.score(), is(2));
    }

    @Test
    public void カードが11なら得点は10() {
        add(11);
        assertThat(hand.score(), is(10));
    }

    @Test
    public void カード1とカード1なら得点は12() {
        add(1);
        add(1);
        assertThat(hand.score(), is(12));
    }

    @Test
    public void カード11とカード13なら得点は20() {
        add(11);
        add(13);
        assertThat(hand.score(), is(20));
    }

    @Test
    public void カード11とカード13とカード5なら得点は25() {
        add(11);
        add(13);
        add(5);
        assertThat(hand.score(), is(25));
    }

    @Test
    public void カード1とカード1とカード1なら得点は13() {
        add(1);
        add(1);
        add(1);
        assertThat(hand.score(), is(13));
    }

    @Test
    public void カード1とカード10ならブラックジャック() {
        add(1);
        add(10);
        assertThat(hand.isBlackJack(), is(true));
    }

    @Test
    public void カード1とカード10とカード11ならブラックジャックではない() {
        add(1);
        add(10);
        add(11);
        assertThat(hand.score(), is(21));
        assertThat(hand.isBlackJack(), is(false));
    }

}
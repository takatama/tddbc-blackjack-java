package tddbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by takatama on 2014/05/17.
 */
public class BlackJack {
    Deck deck;
    Player player;
    Player dealer;

    public BlackJack() {
        deck = new Deck();
        player = new Player(deck);
        dealer = new Player(deck);
    }

    private void display(String message) {
        System.out.println(message);
    }

    private boolean confirm(String message) {
        System.out.print(message + " [y/N]:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line != null && line.toLowerCase().startsWith("y");
    }

    private void displayScores() {
        display("Player: " + player.score());
        display("Dealer: " + dealer.score());
    }

    private void displayResult() {
        switch (player.match(dealer)) {
            case WIN:
                display("You win!");
                break;
            case DRAW:
                display("Draw.");
                break;
            case LOST:
                display("You lose!");
        }
    }

    private void init() {
        deck.init().shuffle();
        player.init().hit().hit();
        dealer.init().hit();
        displayScores();
        dealer.hit();
    }

    private void play() {
        init();

        while(!player.isBust()) {
            if (!confirm("hit?")) {
                break;
            }
            player.hit();
            display("Player: " + player.score());
        }

        if (player.isBust()) {
            display("You bust...");
        }

        while (dealer.score() < 17) {
            dealer.hit();
            display("Dealer: hit");
        }

        displayScores();
        displayResult();
    }

    public void start() {
        while(true) {
            play();
            if(!confirm("Retry?")) {
                break;
            }
        }
    }
}


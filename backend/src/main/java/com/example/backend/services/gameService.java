package com.example.backend.services;

import com.example.backend.models.*;

import java.util.ArrayList;

import static com.example.backend.models.PlayerAction.Action.HIT;

/**
 * Used to manage game state for application. As this is only used for local
 * games, singleton pattern works for this project.
 *
 * Author: Connor Vass
 * Version: 2.0
 * Date: Sept 4th, 2025
 */

public class gameService {
    private Player player;
    private Dealer dealer;
    private Deck deck;

    public gameService(int amountOfDecks, int initialChips) {
        this.deck = new Deck(amountOfDecks);
        this.player = new Player(initialChips);
        this.dealer = new Dealer();
    }

    public void addHand(int ante) {
        if (player.getChips() < ante) {
            throw new IllegalArgumentException("You do not have enough chips");
        }
        player.addHand(ante);
    }


    public void startHand() {
        player.clearHands();
        dealer.WipeCards();
        deck.needToShuffle();

        ArrayList<Card> cardsDealt = deck.dealDeck(player.getHands().size());
        int ctr = 0;
        for (PlayerHand pHand : player.getHands()) {
            pHand.getPlayerHand().addCard(cardsDealt.get(ctr++));
        }
        dealer.getHand().addCard(cardsDealt.get(ctr++));

        for (PlayerHand pHand : player.getHands()) {
            pHand.getPlayerHand().addCard(cardsDealt.get(ctr++));
        }
        dealer.getHand().addCard(cardsDealt.get(ctr++));

        for (PlayerHand pHand : player.getHands()) {
            pHand.hasAutoBlackjack();
        }
    }

    public boolean playerAction(PlayerAction.Action action) {
        PlayerHand hand = player.getActiveHand();
        boolean handActive = true;
        if (hand.getAutoBlackjack()) {
           handActive = false;
        }
        else {
            switch (action) {
                case HIT:
                    handActive = hand.hit(deck.dealCard());
                    break;
                case DOUBLE:
                    if (player.getChips() >= hand.getAnte() && hand.getFirstTurn()) {
                        player.removeChips(hand.getAnte());
                        hand.doubleDown(deck.dealCard());
                        handActive = false;
                    }
                    break;
                case SPLIT:
                    if (player.getChips() >= hand.getAnte() && hand.canSplit()) {
                        player.getHands().add(hand.getHandNumber(), new PlayerHand(hand.getAnte(), player.getHands().size(), hand.getSplitCard(deck.dealCard())));
                    }
                    break;
                case STAND:
                    handActive = false;
                default:
                    throw new IllegalArgumentException("Invalid action");
            }
        }
        if (!handActive) {
            player.nextHand();
        }
        return handActive;
    }



}

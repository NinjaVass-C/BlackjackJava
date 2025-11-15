package com.example.backend.services;

import com.example.backend.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Used to manage game state for application. As this is only used for local
 * games, singleton pattern works for this project.
 *
 * Author: Connor Vass
 * Version: 2.0
 * Date: Sept 4th, 2025
 */
@Service
public class GameService {
    private Player player;
    private Dealer dealer;
    private Deck deck;
    private boolean handRunning = false;
    private boolean emptyCards = false;

    public GameService() {
    }

    public boolean addHand(int ante) {
        //@todo this is what the front end needs currently, should do something with react to fix the card cleaning process
        if (emptyCards) {
            emptyCards = false;
            handRunning = false;
            dealer.WipeCards();
            player.WipeCards();
        }
        if (player.getChips() < ante) {
            return false;
        }
        player.addHand(ante);
        return true;
    }


    public boolean startHand() {
        handRunning = true;
        deck.needToShuffle();

        ArrayList<Card> cardsDealt = deck.dealDeck(player.getHands().size());
        int ctr = 0;
        for (PlayerHand pHand : player.getHands()) {
            pHand.getPlayerHand().addCard(cardsDealt.get(ctr++));
        }
        dealer.getCards().addCard(cardsDealt.get(ctr++));

        for (PlayerHand pHand : player.getHands()) {
            pHand.getPlayerHand().addCard(cardsDealt.get(ctr++));
        }
        dealer.getCards().addCard(cardsDealt.get(ctr++));

        for (PlayerHand pHand : player.getHands()) {
            pHand.hasAutoBlackjack();
        }
        return true;
    }

    /**
     * Check for hand, ensuring user does not get asked to make
     * a turn on an inactive hand
     * @return hand is active or not.
     */
    public boolean checkHandStatus() {
        PlayerHand hand = player.getActiveHand();
        if (hand.getAutoBlackjack()) {
            player.nextHand();
            if (!player.turnOver()) {
                checkHandStatus();
            }
        }
        return true;
    }

    /**
     * Main decision tree for a players turn, seperates actions
     * per each hand the player has.
     * @param action HIT, STAND, DOUBlE, OR SPLIT
     * @return boolean hand is active or not
     */
    public boolean playerAction(PlayerAction.Action action) {
        PlayerHand hand = player.getActiveHand();
        boolean handActive = true;
            switch (action) {
                case HIT:
                    handActive = hand.hit(deck.dealCard());
                    break;
                case DOUBLE:
                    if (player.getChips() >= hand.getAnte() && hand.getFirstTurn()) {
                        player.removeChips(hand.getAnte());
                        hand.doubleDown(deck.dealCard());
                        handActive = false;
                    } else {
                        throw new IllegalArgumentException("Invalid action");
                    }
                    break;
                case SPLIT:
                    if (player.getChips() >= hand.getAnte() && hand.canSplit()) {
                        player.getHands().add(hand.getHandNumber(), new PlayerHand(hand.getAnte(), player.getHands().size(), hand.getSplitCard(deck.dealCard())));
                    }
                    else {
                        throw new IllegalArgumentException("Invalid action");
                    }
                    break;
                case STAND:
                    handActive = false;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid action");
            }

        if (!handActive) {
            player.nextHand();
            if (!player.turnOver()) {
                checkHandStatus();
            }
        }
        if (player.turnOver()) {
            dealer.dealToEnd(deck, player.checkAllHandsBust());
        }
        return handActive;
    }

    public void resolveRound() {
        int dealerCards = dealer.getCards().getHandValue();
        for (PlayerHand pHand : player.getHands()) {
            if (pHand.getAutoBlackjack()) {
                player.addChips((int) Math.ceil(pHand.getAnte() + (pHand.getAnte() * 1.5)));
            }
            else if (pHand.getHandValue() > dealerCards && !pHand.getBust()) {
                player.addChips(pHand.getAnte() * 2);
            }
            else if (pHand.getHandValue() == dealerCards && !pHand.getBust()) {
                player.addChips(pHand.getAnte());
            }
            else if (pHand.getHandValue() < dealerCards && dealerCards > 21 && !pHand.getBust()) {
                player.addChips(pHand.getAnte() * 2);
            }
        }
        handRunning = false;
        emptyCards = true;
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Boolean getHandRunning() { return handRunning; }

    public int getTrueCount() {
        return deck.getTrueCount();
    }

    public void setup(int deckNo, int chips) {
        this.deck = new Deck(deckNo);
        this.player = new Player(chips);
        this.dealer = new Dealer();
    }

}

package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * cards held by each player
 * different from hand history, which is history of hand played
 * Created by Dave on 9/15/2015.
 */
public class Hand {
    private List<Card> hand;
    int handSize;

    public Hand(int handSize) {
        hand = new ArrayList<Card>();
        this.handSize = handSize;
    }

    public void addCard(Card c) {
        hand.add(c);
    }


    /**
     * helper method for PlayCard
     * called if cannot find appropriate suit in Hand
     */

    public Card randomCard() {
        int i = (int) Math.random() * hand.size();
        return hand.get(i);
    }

    /*
        getters + setters
     */

    //prints contents of hand
    public void printHand() {
        for (Card c : hand) {
            c.printCard();
        }
    }

    /**
     * Checks if any card in hand is of given suit
     *
     * @param s Suit to check
     * @return true if contains given suit, false otherwise
     */
    public boolean contains(Suit s) {
        if (s == Suit.DIAMONDS) { //special case for trump
            for (Card card : hand) {
                if (card.isTrump())
                    return true;
            }
            return false;
        }
        for (Card card : hand) { //fail
            if (card.getCardSuit().equals(s) && !card.isTrump())
                return true;
        }
        return false;
    }

    /**
     * checks if Card c is in hand
     *
     * @param c Card in hand
     * @return true if in hand, false otherwise
     */
    public boolean contains(Card c) {
        for (Card card : hand) {
            if (c.getCardSuit().equals(card.getCardSuit()) && c.getCardValue().equals(card.getCardValue()))
                return true;
        }
        return false;
    }

    /**
     * checks if hand contains given card
     *
     * @param id Card id
     * @return true if in hand, false otherwise
     */
    public boolean contains(int id) {
        for (Card c : hand) {
            if (c.getId() == id) return true;
        }
        return false;
    }

    public List<Card> getHand() {
        return hand;
    }

    /**
     * removes target card from hand
     *
     * @return true if success, false otherwise
     */
    public boolean remove(Card c) {
        for (int i = 0; i < hand.size(); i++) {
            if (c.equals(hand.get(i))) {
                hand.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * called by Player after deciding to pick up blind
     * adds blind to hand to make cards viewable and removes from blind
     *
     * @return success
     */
    public boolean addBlindtoHand(Card[] blind) {
        //adds to hand
        for (int i = 0; i < 2; i++) {
            hand.add(blind[i]);
        }
        for (int i = 0; i < 2; i++) {
            blind[i] = null;
        }
        if (blind.length != 0) return false;
        return true;

    }

    /**
     * returns card given id
     * @param id Card id (int 0-31)
     * @return Card, else exits if out of range
     */
    public Card getCard(int id){
        for(Card c : hand){
            if(c.getId()==id)
                return c;
        }
        System.out.println("NO CARD FOUND IN HAND id : " + id);
        System.exit(1);
        return null; //unreachable code
    }
}

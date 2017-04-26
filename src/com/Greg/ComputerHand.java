package com.Greg;

import java.util.ArrayList;

/**
 * Created by admin on 4/26/17.
 */
public class ComputerHand {


    // All of the user's cards, face up and face down
    private ArrayList<Card> userHand = new ArrayList<Card>();
    // The face up cards
    private  ArrayList<Card> faceUpUser = new ArrayList<Card>(/*userHand.subList(0, 2)*/);

    protected void createComputerHand(ArrayList<Card> deck)  {

        for (int x = 0 ; x < 6 ; x++)  {
            int random = (int) ((Math.random() * deck.size()));     // Generate random number
            Card card = deck.get(random);   // Use random number to pull a random card (key/value pair) from deck
            deck.remove(card);    // Remove random card from the deck
            userHand.add(card);    // Add card to user's hand
        }

        // move two cards to faceUpUser. Since they are dealt randomly, move the first two.
        faceUpUser.add(userHand.get(0));
        faceUpUser.add(userHand.get(1));

        System.out.println(faceUpUser);
    }     //(OUTER COMMENT) This method draws random cards from deck to create the user's hand


    protected void printHand() {
        System.out.println("Face up cards: " + faceUpUser);
        System.out.println("For debugging (remove for the real game) all the card: " + userHand);
    }


    public void removeRandomAddNewToHand(Card newCard) {
        int randomHand = (int) ((Math.random() * userHand.size()));     // Creates random number within range of the cards in user's hand
        Card removeCard = userHand.get(randomHand);       // Associates random number with a corresponding card in user's hand
        userHand.remove(removeCard);       // Removes the corresponding card from user's hand
        faceUpUser.add(newCard);    // Swaps card from deck pile with card from user's hand
        userHand.add(newCard);
    }

    public boolean areAllCardsFaceUp() {
        if (faceUpUser.size() == 6) {
            return true;
        }

        return false;    /// TODO verify that this is the correct test
    }
}

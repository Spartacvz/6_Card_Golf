package com.Greg;

import java.util.ArrayList;

public class UserHand {


    // All of the user's cards, face up and face down
    protected ArrayList<Card> userHand = new ArrayList<Card>();
    // The face up cards
    protected ArrayList<Card> faceUpUser;

    protected void createUserHand(ArrayList<Card> deck)  {

        for (int x = 0 ; x < 6 ; x++)  {
            int random = (int) ((Math.random() * deck.size()));     // Generate random number
            Card card = deck.get(random);   // Use random number to pull a random card (key/value pair) from deck
            deck.remove(card);    // Remove random card from the deck
            userHand.add(card);    // Add card to user's hand
        }
        faceUpUser = new ArrayList<Card>(userHand.subList(0, 2));
    }     //(OUTER COMMENT) This method draws random cards from deck to create the user's hand

    protected void printHand() {
        System.out.println("User Hand: " + faceUpUser);
    }

    protected void removeRandomAddNewToHand(Card newCard) {
        int randomHand = (int) ((Math.random() * userHand.size()));     // Creates random number within range of the cards in user's hand
        Card removeCard = userHand.get(randomHand);       // Associates random number with a corresponding card in user's hand
        userHand.remove(removeCard);       // Removes the corresponding card from user's hand
        faceUpUser.add(newCard);    // Swaps card from deck pile with card from user's hand
    }

    protected boolean areAllCardsFaceUp() {
        if (faceUpUser.size() == 6) {
            return true;    }
            return false;
    }
}

package com.Greg;

import java.util.ArrayList;

public class ComputerHand {


    // All of the computer's cards, face up and face down
    protected ArrayList<Card> computerHand = new ArrayList<Card>();
    // The face up cards
    protected ArrayList<Card> faceUpComp;

    protected void createComputerHand(ArrayList<Card> deck)  {

        for (int x = 0 ; x < 6 ; x++)  {
            int random = (int) ((Math.random() * deck.size()));     // Generate random number
            Card card = deck.get(random);   // Use random number to pull a random card (key/value pair) from deck
            deck.remove(card);    // Remove random card from the deck
            computerHand.add(card);    // Add card to computer's hand
        }
        faceUpComp = new ArrayList<Card>(computerHand.subList(0, 2));
    }     //(OUTER COMMENT) This method draws random cards from deck to create the computer's hand

    protected void printHand() {
        System.out.println("Computer Hand: " + faceUpComp);
    }

    protected void removeRandomAddNewToHand(Card newCard) {
        int randomHand = (int) ((Math.random() * computerHand.size()));     // Creates random number within range of the cards in computer's hand
        Card removeCard = computerHand.get(randomHand);       // Associates random number with a corresponding card in computer's hand
        computerHand.remove(removeCard);       // Removes the corresponding card from computer's hand
        faceUpComp.add(newCard);    // Swaps card from deck pile with card from computer's hand
    }

    protected boolean areAllCardsFaceUp() {
        if (faceUpComp.size() == 6) {
            return true;    }
            return false;
    }
}

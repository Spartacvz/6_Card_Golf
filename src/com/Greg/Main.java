package com.Greg;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    private static Scanner pile = new Scanner(System.in);
    private static Scanner drawn = new Scanner(System.in);

    private static ArrayList<Card> deck = new ArrayList<Card>();
    private static ArrayList<Card> discard = new ArrayList<Card>();

    private static UserHand userHand;       // Initialize userHand variable
    private static ComputerHand computerHand;       // Initialize computerHand variable


    public static void main(String[] args) {
        Deck();        // This method Creates a 52 card deck

        userHand = new UserHand();        // This creates UserHand object to represent the hand of the user
        userHand.createUserHand(deck);
        userHand.printHand();

        computerHand = new ComputerHand();        // This creates ComputerHand object to represent the hand of the computer
        computerHand.createComputerHand(deck);
        computerHand.printHand();

        DiscardPile();      // This method draws a single random card from remaining cards in deck and turns it face up to start discard pile
        UserTurn();     // This method defines the steps and actions of the user's turn
        ComputerTurn();
        GameOver();
    }


    protected static void GameOver() {
        // Check if everything is face up?
        if (userHand.areAllCardsFaceUp() && computerHand.areAllCardsFaceUp()) {
            System.out.println("The Game is over! I will now tally the totals");
            int compTotal = 0;
            int userTotal = 0;
            int i;
            int ii;

            for(i = 1; i < computerHand.faceUpComp.size(); i++)
                compTotal = computerHand.faceUpComp.get(i).value;

            for(ii = 1; ii < userHand.faceUpUser.size(); ii++)
                userTotal = userHand.faceUpUser.get(ii).value;

            System.out.println("My total is " + compTotal + "and your total is " + userTotal + ".");
            if (userTotal > compTotal)  {
                System.out.println("Congratulations, looks like you win!");
            }
            else    {
                System.out.println("Sorry buddy, you lose this one!");
            }
        }
    }

    protected static void Deck() {

        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (int i = 0; i < 4; i++) {  // Add cards to deck

            for (int r = 0; r < ranks.length; r++) {

                Card card = new Card();
                card.rank = ranks[r];
                card.value = r + 1;

                if (card.rank.equals("2")) {
                    card.value = -2;
                } else if (card.rank.equals("J") || card.rank.equals("Q")) {
                    card.value = 10;
                } else if (card.rank.equals("K")) {
                    card.value = 0;
                }
                deck.add(card);
            }
        }
    }

    protected static void DiscardPile() {
        int random = (int) ((Math.random() * deck.size())); // Generate random number
        Card card = deck.get(random);   // Use random number to pull a random card (key/value pair) from deck
        discard.add(card);

        System.out.println("DISCARD PILE: " + discard);
    }      //(OUTER COMMENT) This method draws a single random card from remaining cards in deck and turns it face up to start discard pile

    protected static void UserTurn() {     // This method defines the steps and actions of the user's turn
        System.out.println("Press '1' to draw from Deck pile or '2' to draw from Discard pile");        // Gives user the choice to draw from deck or discard pile
        int draw = pile.nextInt();      // Creates variable to contain user response
        if (draw == 1) {
            DrawnFromDeck();
        } else if (draw == 2) {
            DrawnFromDiscard();
        } else {
            System.out.println("I'm sorry, that entry was invalid, please try again!");
            UserTurn();
        }
        EndTurn();
        GameOver();
        ComputerTurn();
    }     //(OUTER COMMENT) This method defines the steps and actions of the user's turn

    protected static void DrawnFromDeck() {
        int random = (int) ((Math.random() * deck.size()));        // Creates random number within range of the cards in deck pile
        Card card = deck.get(random);       // Associates random number with a corresponding card in deck pile
        deck.remove(card);       // Removes the corresponding card from deck pile
        System.out.println("SELECTED CARD: " + card);       // Displays selected card for user to see
        System.out.println("");

        System.out.println("Press '1' to add to hand or '2' to Discard");        // Gives user the choice to place card in face up hand or discard pile
        int handOrDiscard = drawn.nextInt();      // Creates variable to contain user response
        if (handOrDiscard == 1) {       // Determines whether user has picked face up hand or not
            userHand.removeRandomAddNewToHand(card);
        } else if (handOrDiscard == 2) {       // Determines whether user has picked discard pile or not
            discard.add(card);    // Add card from deck pile to discard pile
        } else {
            System.out.println("I'm sorry, that entry was invalid, please try again!");       // Determines user has not picked a valid entry
            DrawnFromDeck();       // Forces user to try again after invalid entry
        }
        System.out.println("That conclude's your turn, I will now play");
        ComputerTurn();
    }       //(OUTER COMMENT) This method allows a user to decide where to place a card that has been drawn from the deck pile

    protected static void DrawnFromDiscard() {
        Card card = discard.remove(discard.size() - 1);
        System.out.println("SELECTED CARD :" + card);       // Displays selected card for user to see

        System.out.println("Press '1' to add to hand or '2' to Discard");
        int handOrDiscard = drawn.nextInt();       // Creates variable to contain user response
        if (handOrDiscard == 1) {       // Determines whether user has chosen face up hand or not
            userHand.removeRandomAddNewToHand(card);
        } else if (handOrDiscard == 2) {        // Determines whether user has chosen discard pile
            discard.add(card);    // Add card to discard pile
        } else {
            System.out.println("I'm sorry, that entry was invalid, please try again!");
            DrawnFromDiscard();
        }
        System.out.println("That conclude's your turn, I will now play");
        ComputerTurn();
    }       //(OUTER COMMENT) This method allows a user to decide where to place a card that has been drawn from the discard pile

    protected static void EndTurn() {
        // Ending sequence for every turn, displays visible changes
        System.out.println("");
        userHand.printHand();       // Displays user's face up cards at end of turn
        computerHand.printHand();       // Displays computer's face up cards at end of turn

        if (discard.isEmpty())  {
            System.out.println("DISCARD PILE: " + discard);
        }
        else {
            System.out.println("DISCARD PILE: " + discard.get(discard.size() - 1));     // Displays top card in Discard Pile at end of turn
        }
        System.out.println();
    }       //(OUTER COMMENT) This method displays all current information for the hands and discard pile

    protected static void ComputerTurn() {
        System.out.println("I am going to draw from the deck this time");
        int random = (int) ((Math.random() * deck.size()));        // Creates random number within range of the cards in deck pile
        Card card = deck.get(random);       // Associates random number with a corresponding card in deck pile
        deck.remove(card);       // Removes the corresponding card from deck pile
        System.out.println("SELECTED CARD: " + card);       // Displays selected card for user to see
        System.out.println("");

        if (card.value >= 4)    {
            System.out.println("I don't need this card, I'm going to discard it.");
            discard.add(card);
        }
        else if(card.value >= 2){
            System.out.println("This is a good card, I'm going to keep this one.");
            computerHand.removeRandomAddNewToHand(card);
        }
        else {
            System.out.println("Wow, what a great card, I really needed this one.");
            computerHand.removeRandomAddNewToHand(card);
        }
        EndTurn();
        GameOver();
        UserTurn();
    }
}
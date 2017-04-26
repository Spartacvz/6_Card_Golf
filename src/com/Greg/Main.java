package com.Greg;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    private static Scanner pile = new Scanner(System.in);
    private static Scanner drawn = new Scanner(System.in);

    private static ArrayList<Card> deck = new ArrayList<Card>();
//    private static ArrayList<String> userHand = new ArrayList<String>();
//    /// TODO
//    private static ArrayList<String> faceUpUser = new ArrayList<String>(/*userHand.subList(0, 2)*/);
    //private static ArrayList<String> computerHand = new ArrayList<String>();
    /// TODO
    private static ArrayList<Card> faceUpComp = new ArrayList<Card>(/*computerHand.subList(0, 2)*/);
    private static ArrayList<Card> discard = new ArrayList<Card>();

    private static Hand userHand;
    private static ComputerHand computerHand;

    public static void main(String[] args) {
        Deck();     // This method Creates a 52 card deck

        userHand = new Hand();
        userHand.createUserHand(deck);

        userHand.printHand();

        computerHand = new ComputerHand();
        computerHand.createComputerHand(deck);
        computerHand.printHand();

        DiscardPile();      // This method draws a single random card from remaining cards in deck and turns it face up to start discard pile

        while (!gameOver()) {

            //UserHand();     // This method draws random cards from deck to create the user's hand
            System.out.println("");
            //ComputerHand();     // This method draws random cards from deck to create the computer's hand
            System.out.println("");

            System.out.println("");
            UserTurn();     // This method defines the steps and actions of the user's turn
            ComputerTurn();
        }
    }

    private static boolean gameOver() {
        // Check if everything is face up?
        if (userHand.areAllCardsFaceUp() && computerHand.areAllCardsFaceUp()) {
            return true;
        }

        return false;

    }


    private static void Deck() {

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
                }

                // TODO any other values to set? King?

                deck.add(card);
            }
        }
    }

//            deck.add("A");
//            deck.add("2");
//            deck.add("3");
//            deck.add("4");
//            deck.add("5");
//            deck.add("6");
//            deck.add("7");
//            deck.add("8");
//            deck.add("9");
//            deck.add("10");
//            deck.add("J");
//            deck.add("Q");
//            deck.add("K");
//            }
//        }     //(OUTER COMMENT) This method Creates a 52 card deck
//    }
//    private static void UserHand()  {
//
//        for (int x = 0 ; x < 6 ; x++)  {
//            int random = (int) ((Math.random() * deck.size()));     // Generate random number
//            String card = deck.get(random);   // Use random number to pull a random card (key/value pair) from deck
//            deck.remove(card);    // Remove random card from the deck
//            userHand.add(card);    // Add card to user's hand
//        }
//        System.out.println(faceUpUser);
//    }     //(OUTER COMMENT) This method draws random cards from deck to create the user's hand
//
//    private static void ComputerHand()  {
//
//        for (int x = 0 ; x < 6 ; x++)  {
//            int random = (int) ((Math.random() * deck.size())); // Generate random number
//            String card = deck.get(random);   // Use random number to pull a random card (key/value pair) from deck
//            deck.remove(random);    // Remove random card from the deck
//            computerHand.add(card);    // Add card to UserHand
//        }
//        System.out.println(faceUpComp);
//    }     //(OUTER COMMENT) This method draws random cards from deck to create the computer's hand

    private static void DiscardPile()   {
        int random = (int) ((Math.random() * deck.size())); // Generate random number
        Card card = deck.get(random);   // Use random number to pull a random card (key/value pair) from deck
        discard.add(card);

        System.out.println("DISCARD PILE: " + discard);
    }      //(OUTER COMMENT) This method draws a single random card from remaining cards in deck and turns it face up to start discard pile

    private static void UserTurn()  {     // This method defines the steps and actions of the user's turn
        System.out.println("Press '1' to draw from Deck pile or '2' to draw from Discard pile");        // Gives user the choice to draw from deck or discard pile
        int draw = pile.nextInt();      // Creates variable to contain user response
        if (draw == 1) {
            DrawnFromDeck();
            EndTurn();
        }
        else if (draw == 2) {
            DrawnFromDiscard();
            EndTurn();
        }
        else    {
            System.out.println("I'm sorry, that entry was invalid, please try again!");
            UserTurn();
        }
    }     //(OUTER COMMENT) This method defines the steps and actions of the user's turn

    private static void DrawnFromDeck() {
        int random = (int) ((Math.random() * deck.size()));        // Creates random number within range of the cards in deck pile
        Card card = deck.get(random);       // Associates random number with a corresponding card in deck pile
        deck.remove(card);       // Removes the corresponding card from deck pile
        System.out.println("SELECTED CARD: " + card);       // Displays selected card for user to see
        System.out.println("");

        System.out.println("Press '1' to add to hand or '2' to Discard");        // Gives user the choice to place card in face up hand or discard pile
        int handOrDiscard = drawn.nextInt();      // Creates variable to contain user response
        if (handOrDiscard == 1) {       // Determines whether user has picked face up hand or not
            userHand.removeRandomAddNewToHand(card);
        }
        else if (handOrDiscard == 2)  {       // Determines whether user has picked discard pile or not
            discard.add(card);    // Add card from deck pile to discard pile
        }
        else    {
            System.out.println("I'm sorry, that entry was invalid, please try again!");       // Determines user has not picked a valid entry
            DrawnFromDeck();       // Forces user to try again after invalid entry
        }
    }       //(OUTER COMMENT) This method allows a user to decide where to place a card that has been drawn from the deck pile

    private static void DrawnFromDiscard() {
        Card card = discard.remove(discard.size() - 1);
        System.out.println("SELECTED CARD :" + card);       // Displays selected card for user to see

        System.out.println("Press '1' to add to hand or '2' to Discard");
        int handOrDiscard = drawn.nextInt();       // Creates variable to contain user response
        if (handOrDiscard == 1) {       // Determines whether user has chosen face up hand or not
           userHand.removeRandomAddNewToHand(card);
// int randomHand = (int) ((Math.random() * userHand.size()));     // Creates random number within range of the cards in user's hand
//            String card1 = userHand.get(randomHand);       // Associates random number with a corresponding card in user's hand
//            userHand.remove(card1);       // Removes the corresponding card from user's hand
//            faceUpUser.add(card);    // Swaps card from deck pile with card from user's hand
        } else if (handOrDiscard == 2) {        // Determines whether user has chosen discard pile
            discard.add(card);    // Add card to discard pile
        } else {
            System.out.println("I'm sorry, that entry was invalid, please try again!");
            DrawnFromDiscard();
        }
    }       //(OUTER COMMENT) This method allows a user to decide where to place a card that has been drawn from the discard pile

    private static void EndTurn() {
        // Ending sequence for every turn, displays visible changes
        System.out.println("");
        System.out.println("USER: ");
        userHand.printHand();
        System.out.println("");
        System.out.println("COMPUTER: " + faceUpComp);
        System.out.println("");
        System.out.println("DISCARD PILE: " + discard.get(discard.size() - 1));
        System.out.println();
        System.out.println("That conclude's your turn, I will now play");
        ComputerTurn();
    }       //(OUTER COMMENT) This method displays all current information for the hands and discard pile

    private static void ComputerTurn()  {
        int A = 1;
        int K = 0;
        int Q = 10;
        int J = 10;

        // TODO computer strategy here.

    }     //(OUTER COMMENT) This method defines the steps and actions of the computer's turn
}
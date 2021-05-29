package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Deal {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("takes two args: num-hands & card-per-hand");
        }

        int numHands = Integer.parseInt(args[0]);
        int cardsPerHand = Integer.parseInt(args[1]);

        String[] suits = new String[] {
            "spades", "hearts", 
            "diamonds", "clubs" 
        };
        String[] ranks = new String[] {
            "ace", "2", "3", "4",
            "5", "6", "7", "8", "9", "10", 
            "jack", "queen", "king" 
        };

        // List<String> deck = new ArrayList<>();
        Set<String> cards = new HashSet<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(rank + " of " + suit);
            }
        }
        List<String> deck = cards.stream().collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(deck);

        if (numHands * cardsPerHand > deck.size()) {
            System.err.format("Not enough cards, expect %d but only got %d", numHands * cardsPerHand, deck.size());
        }
        
        for (int i = 0; i < numHands; i++) {
            System.out.println(dealHand(deck, cardsPerHand));
        }
    }

    public static <E> List<E> dealHand(List<E> deck, int n) {
        int size = deck.size();
        List<E> handView = deck.subList(size - n, size);
        List<E> hand = new ArrayList<>(handView);
        // List<E> hand = handView.stream().collect(Collectors.toCollection(ArrayList::new));
        handView.clear();
        return hand;
    }
}

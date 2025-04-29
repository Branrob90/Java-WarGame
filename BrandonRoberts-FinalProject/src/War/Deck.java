package War;

import java.util.*;

public class Deck 
{
    private List<Card> deck;

    public Deck() 
    {
        deck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        
        for (String suit : suits) 
        {
            for (String rank : ranks) 
            {
                deck.add(new Card(rank, suit));
            }
        }

        shuffle();
    }

    public void shuffle() 
    {
        Collections.shuffle(deck);
    }

    public Card dealCard() 
    {
        return deck.isEmpty() ? null : deck.remove(0);
    }

    public int remainingCards() 
    {
        return deck.size();
    }
}

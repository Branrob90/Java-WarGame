package War;

import java.util.*;

public class Player 
{
    private List<Card> hand;
    private String name;

    public Player(String name) 
    {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void addCard(Card card) 
    {
        hand.add(card);
    }

    public Card playCard() 
    {
        return hand.isEmpty() ? null : hand.remove(0);
    }

    public boolean hasCards() 
    {
        return !hand.isEmpty();
    }

    public int getCardCount() 
    {
        return hand.size();
    }

    public String getName() 
    {
        return name;
    }

    public void addCards(List<Card> cards) 
    {
        hand.addAll(cards);
    }
}

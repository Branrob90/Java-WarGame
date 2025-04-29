package War;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class WarGame extends JFrame 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Player player1;
    private Player player2;
    private Deck deck;
    private JLabel statusLabel;
    private JButton nextRoundButton;
    private JPanel cardPanel;
    private JLabel backLabel1;
    private JLabel backLabel2;
    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;



    public WarGame() 
    {
        setTitle("War Card Game");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize players and deck
        deck = new Deck();
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");

        while (deck.remainingCards() > 0) 
        {
            player1.addCard(deck.dealCard());
            player2.addCard(deck.dealCard());
        }

        // Initialize game controls
        nextRoundButton = new JButton("Next Round");
        nextRoundButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                playRound();
            }
        });

        statusLabel = new JLabel("Game Started! Player 1: " + player1.getCardCount() + " cards, Player 2: " + player2.getCardCount() + " cards.");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        cardPanel = new JPanel(new BorderLayout()); // Now using BorderLayout

     // Create the three sub-panels
     leftPanel = new JPanel();
     centerPanel = new JPanel();
     rightPanel = new JPanel();

     // Add the back images to left and right sides
     leftPanel.add(new JLabel(new ImageIcon("image/back.png")));
     rightPanel.add(new JLabel(new ImageIcon("image/back.png")));

     // Add sub-panels into cardPanel
     cardPanel.add(leftPanel, BorderLayout.WEST);
     cardPanel.add(centerPanel, BorderLayout.CENTER);
     cardPanel.add(rightPanel, BorderLayout.EAST);


        add(statusLabel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(nextRoundButton, BorderLayout.SOUTH);
    }

    private void playRound() 
    {
        if (!player1.hasCards() || !player2.hasCards()) 
        {
            String winner = player1.hasCards() ? player1.getName() : player2.getName();
            statusLabel.setText(winner + " wins the game!");
            nextRoundButton.setEnabled(false);
            return;
        }
        

        // Player plays one card each
        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        // Update display
        statusLabel.setText(player1.getName() + " plays: " + card1 + " | " + player2.getName() + " plays: " + card2);
        
        // Start with showing the back of the cards then flip them over
        backLabel1 = new JLabel(new ImageIcon("image/back.png"));
        backLabel2 = new JLabel(new ImageIcon("image/back.png"));
        cardPanel.add(backLabel1);
        cardPanel.add(backLabel2);
        Timer timer = new Timer(500, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                // After half a second, flip the backs to the real cards!
                centerPanel.removeAll();

                JLabel cardLabel1 = new JLabel(card1.getImage());
                JLabel cardLabel2 = new JLabel(card2.getImage());
                centerPanel.add(cardLabel1);
                centerPanel.add(cardLabel2);

                revalidate();
                repaint();
            }
        });
        timer.setRepeats(false); // Only fire once
        timer.start();


        // Add the winner's cards to their deck
        if (card1.getValue() > card2.getValue()) 
        {
            player1.addCard(card1);
            player1.addCard(card2);
        } 
        else if (card1.getValue() < card2.getValue()) 
        {
            player2.addCard(card1);
            player2.addCard(card2);
        } 
        else 
        {
            // It's a tie: WAR scenario
            warRound(card1, card2);
        }

        // Update the game status
        statusLabel.setText(player1.getName() + " has " + player1.getCardCount() + " cards, " + player2.getName() + " has " + player2.getCardCount() + " cards.");
    }

    private void warRound(Card card1, Card card2) 
    {
        // War animation logic (same structure as the round but involves adding multiple cards to the pile)
        statusLabel.setText("It's a tie! WAR!");

        java.util.List<Card> warPile = new ArrayList<>();
        warPile.add(card1);
        warPile.add(card2);

        // Simulate the war (both players need to place 3 more cards)
        for (int i = 0; i < 3; i++) 
        {
            warPile.add(player1.playCard());
            warPile.add(player2.playCard());
        }

        // Compare the next set of cards
        card1 = player1.playCard();
        card2 = player2.playCard();

        JLabel warCard1 = new JLabel(card1.getImage());
        JLabel warCard2 = new JLabel(card2.getImage());


        // Display cards involved in the war
        centerPanel.removeAll();
        centerPanel.add(warCard1);
        centerPanel.add(warCard2);
        revalidate();
        repaint();

        if (card1.getValue() > card2.getValue()) 
        {
            player1.addCards(warPile);
            statusLabel.setText(player1.getName() + " wins the war!");
        } 
        else 
        {
            player2.addCards(warPile);
            statusLabel.setText(player2.getName() + " wins the war!");
        }
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                WarGame game = new WarGame();
                game.setVisible(true);
            }
        });
        System.out.println("Working directory: " + System.getProperty("user.dir"));

    }
}


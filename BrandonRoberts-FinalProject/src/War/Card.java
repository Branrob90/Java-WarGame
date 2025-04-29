package War;

import javax.swing.ImageIcon;
import java.io.File;

public class Card 
{
    private String rank;
    private String suit;
    private String imagePath;
    private ImageIcon image;

    public Card(String rank, String suit) 
    {
        this.rank = rank;
        this.suit = suit;
        this.imagePath = "image/" + rank + suit + ".png";

        ImageIcon originalIcon = new ImageIcon(imagePath);

        if (originalIcon.getIconWidth() == -1) 
        {
            System.out.println("Image not found: " + imagePath);
        } 
        else 
        {
            // Resize
            java.awt.Image scaledImage = originalIcon.getImage().getScaledInstance(100, 145, java.awt.Image.SCALE_SMOOTH);
            this.image = new ImageIcon(scaledImage);
        }
    }

    
    public ImageIcon getImage()
    {
    	return image;
    }
    public String getRank() 
    {
        return rank;
    }

    public String getSuit() 
    {
        return suit;
    }

    public int getValue() 
    {
        switch (rank) 
        {
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "J": return 11;
            case "Q": return 12;
            case "K": return 13;
            case "A": return 14;
            default: return 0;
        }
    }
    
    public String getImagePath()
    {
    	return imagePath;
    }

    @Override
    public String toString() 
    {
        return rank + " of " + suit;
    }
}

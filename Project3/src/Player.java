// In The Name Of GOD

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * this abstract class simplifies a player.
 * this class contains necessary information about a player and its an abstract class for its move method.
 * my comment for move method explains different return numbers and their meaning to my playerManager.
 */

public abstract class Player implements Strings {
    protected int score; // is the score that currently player has
    protected int id;
    protected ArrayList<Card> cardArrayList; // cards that the player have
    protected String color; // is a unique color for the player

    /**
     * constructor
     *
     * @param id is the id of the player for game
     */
    public Player(int id) {
        this.id = id;
        cardArrayList = new ArrayList<>();
        switch (id) {
            case 0:
                color = ANSI_BLUE;
                break;
            case 1:
                color = ANSI_RED;
                break;
            case 2:
                color = ANSI_BLACK;
                break;
            case 3:
                color = ANSI_CYAN;
                break;
            case 4:
                color = ANSI_GREEN;
                break;
            case 5:
                color = ANSI_PURPLE;
                break;
            case 6:
                color = ANSI_WHITE;
                break;
            default:
                color = ANSI_YELLOW;
        }

    }

    /**
     * this method prints a players information such as its place in the final table. with it's own color
     * @param place is the rank of the player in the final ranking
     */
    void print(int place) {
        System.out.println((score > 0 ? color : ANSI_YELLOW) + "Player " + (id + 1) + " in place " + (place + 1) +
                " with score " + score + " " + (score != 0 ? "" : winner) + ANSI_RESET);
    }

    /**
     * this method is for checking the availability of the input that users give for choosing a card to playing with.
     * @param x is a row number
     * @param y is a column number
     * @return true if (x,y) exist.
     */
    boolean valid(int x, int y) {
        return x * 8 + y < cardArrayList.size() && y < 8 && y >= 0 && x >= 0;
    }

    /**
     * this method prints a user cards in order of their appearing in cardArrayList.
     * it prints 8 cards in each row.
     */
    protected void printUserCards() {
        int sz = cardArrayList.size();
        for (int i = 0; i < (sz + 7) / 8; i++) {
            int st = i * 8, len = (sz - st > 7 ? 8 : sz % 8);
            for (int j = st; j < len + st; j++)
                System.out.print(cardArrayList.get(j).getColor() + "|$$$$$$$$$$$$$$$|    ");
            System.out.println();

            for (int j = st; j < len + st; j++)
                System.out.print(cardArrayList.get(j).getColor() + "|               |    ");
            System.out.println();

            for (int j = st; j < len + st; j++) {
                System.out.print(cardArrayList.get(j).getColor());
                if (!cardArrayList.get(j).getMove().equals("")) {
                    System.out.print("|    " + cardArrayList.get(j).getMove());
                    for (int k = 4 + cardArrayList.get(j).getMove().length(); k < 15; k++)
                        System.out.print(" ");
                    System.out.print("|    ");
                } else {
                    System.out.print("|       " + cardArrayList.get(j).getNumber() + "       |    ");
                }
            }
            System.out.println();

            for (int j = st; j < len + st; j++)
                System.out.print(cardArrayList.get(j).getColor() + "|               |    ");
            System.out.println();

            for (int j = st; j < len + st; j++)
                System.out.print(cardArrayList.get(j).getColor() + "|$$$$$$$$$$$$$$$|    ");
            System.out.println();

            System.out.println();
        }
    }

    /**
     *  this method deletes a card from a user.
     * @param cardToRemove is the card to be removed from a player cardArrayList
     */
    void removeCard(Card cardToRemove) {
        Iterator<Card> iterator = cardArrayList.iterator();
        while (iterator.hasNext()) {
            Card tmp = iterator.next();
            if (tmp.equals(cardToRemove)) {
                score -= tmp.getScore();
                iterator.remove();
                break;
            }
        }
    }

    /**
     * unlike the previous method this one will add a specific card to the players ArrayList
     * @param card is the card to be added for this player
     */
    void addCard(Card card) {
        cardArrayList.add(card);
        score += card.getScore();
    }

    /**
     * this method modifies the color of the top card of the table if it's wild.
     * whether by asking user or randomly choosing a color
     * this method overrides in computer class.
     * @param cardSheet is the cardSheet of the game
     * @param scanner is the scanner for getting the input
     * @return 3 if it was successful
     */
    protected int topModify(CardSheet cardSheet, Scanner scanner) {
        boolean flag = true;
        while (flag) {
            flag = false;
            System.out.println(color + "Please enter a color for the cards: [y] yellow [b] blue [r] red [g] green");
            char inp = scanner.next().charAt(0);
            if (inp == 'y')
                cardSheet.getOnTop().setColor(ANSI_YELLOW);
            else if (inp == 'b')
                cardSheet.getOnTop().setColor(ANSI_BLUE);
            else if (inp == 'r')
                cardSheet.getOnTop().setColor(ANSI_RED);
            else if (inp == 'g')
                cardSheet.getOnTop().setColor(ANSI_GREEN);
            else {
                System.out.println("What Are you doing ?!\n Try again");
                flag = true;
            }
        }
        return 3;
    }

    public abstract int move(Scanner scanner, CardSheet cardSheet); // -1 -> nashod, 3 -> shod, 2 -> reverse
}

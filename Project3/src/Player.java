// In The Name Of GOD

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public abstract class Player implements Strings {
    protected int score;
    protected int id;
    protected ArrayList<Card> cardArrayList;
    protected String color;

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

    void print(int place) {
        System.out.println((score > 0 ? color : ANSI_YELLOW) + "Player " + (id + 1) + " in place " + (place + 1) +
                " with score " + score + " " + (score != 0 ? "" : winner) + ANSI_RESET);
    }

    boolean valid(int x, int y) {
        return x * 4 + y < cardArrayList.size() && y < 4 && y >= 0 && x >= 0;
    }

    protected void printUserCards() {
        int sz = cardArrayList.size();
        for (int i = 0; i < (sz + 3) / 4; i++) {
            int st = i * 4, len = (sz - i * 4 > 3 ? 4 : sz % 4);
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

    void addCard(Card card) {
        cardArrayList.add(card);
        score += card.getScore();
    }

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

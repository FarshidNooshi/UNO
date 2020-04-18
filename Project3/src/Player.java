// In The Name Of GOD
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Player implements Strings {
    protected int score;
    protected int id;
    protected ArrayList<Card> cardArrayList;
    protected String color;

    public Player(int id) {
        this.id = id;
        cardArrayList = new ArrayList<>();
        score = 0;
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

    public void print(int place) {

    }

    protected void printUserCards() {
        int sz = cardArrayList.size();
        for (int i = 0; i < (sz + 3) / 4; i++) {
            int st = i * 4, len = (sz - i * 4 > 3 ? 4 : sz % 4);
            for (int j = st; j < len + st; j++)
                System.out.print(cardArrayList.get(j).getColor() + "|€€€€€€€€€€€€€€€|    ");
            System.out.println();

            for (int j = st; j < len + st; j++)
                System.out.print(cardArrayList.get(j).getColor() + "|               |    ");
            System.out.println();

            for (int j = st; j < len + st; j++) {
                if (!cardArrayList.get(j).getMove().equals("")) {
                    System.out.println("|      " + cardArrayList.get(j).getMove());
                    for (int k = 6 + cardArrayList.get(j).getMove().length(); k < 15; k++)
                        System.out.print(" ");
                    System.out.print("|    ");
                } else {
                    System.out.print("|      " + cardArrayList.get(j).getNumber() + "        |    ");
                }
            }
            System.out.println();

            for (int j = st; j < len + st; j++)
                System.out.print(cardArrayList.get(j).getColor() + "|               |    ");
            System.out.println();

            for (int j = st; j < len + st; j++)
                System.out.print(cardArrayList.get(j).getColor() + "|€€€€€€€€€€€€€€€|    ");
            System.out.println();

            System.out.println();
        }
    }

    public void addCard(Card card) {
        cardArrayList.add(card);
        score += card.getScore();
    }

    public abstract int move(Scanner scanner, CardSheet cardSheet); // -1 -> nashod, 0 -> adad, 1 -> skip, 2 -> reverse, 3-> 2+, 4 -> wildcol, 5->wildcol+4
}

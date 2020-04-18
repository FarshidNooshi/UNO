// In The Name Of GOD
import java.util.Scanner;

public class Human extends Player {
    public Human(int id) {
        super(id);
    }

    @Override
    public int move(Scanner scanner, CardSheet cardSheet) {
        cardSheet.getOnTop().print();
        printUserCards();

        boolean can = false;

        for (Card card : cardArrayList)
            if (cardSheet.valid(this, card))
                can = true;

        if (!can) {
            this.addCard(cardSheet.getNewCard());

        }
        System.out.println(color + "please choose a card to play with in format i j");
        System.out.println("i means row number and j means column number. card at the top left corner is 1 1" + ANSI_RESET);

        int row = scanner.nextInt() - 1, column = scanner.nextInt() - 1;
        Card selected = cardArrayList.get(row * 4 + column);
        if (!cardSheet.valid(this, selected))
            return -1;

        return -1;
    }

}

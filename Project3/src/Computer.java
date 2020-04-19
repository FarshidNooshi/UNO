// In The Name Of GOD

import java.util.Scanner;

public class Computer extends Player {

    RandomGen randomGen;

    public Computer(int id) {
        super(id);
        randomGen = new RandomGen();
    }

    @Override
    public int move(Scanner scanner, CardSheet cardSheet) {
        if (cardSheet.getOnTop().getMove().equals("Skip") && cardSheet.getUsed() == 0) {
            cardSheet.setUsed(-1);
            System.out.println(color + "Skipped" + ANSI_RESET);
            return 1;
        } else if (cardSheet.getOnTop().getMove().equals("Draw2") && cardSheet.getUsed() != -1) {
            boolean can = false;
            for (Card card : cardArrayList)
                if (card.getMove().equals("Draw2")) {
                    can = true;
                    break;
                }
            if (!can) {
                int cnt = cardSheet.getUsed();
                cnt = (cnt + 1) * 2;
                System.out.println(color + "received " + cnt + " cards." + ANSI_RESET);
                while (cnt-- > 0)
                    addCard(cardSheet.getNewCard());
                cardSheet.setUsed(-1);
                return 3;
            }

            int row = randomGen.random((cardArrayList.size() + 3) / 4);
            int column = randomGen.random(4);
            if (!valid(row, column))
                return -1;
            Card selected = cardArrayList.get(row * 4 + column);

            if (selected.getMove().equals("Draw2")) {
                int num = cardSheet.getUsed();
                cardSheet.setOnTop(selected);
                removeCard(selected);
                cardSheet.setUsed(num + 1);
            } else
                return -1;
            return 3;
        } else if (cardSheet.getOnTop().getMove().equals("WildDraw") && cardSheet.getUsed() != -1) {
            boolean can = false;
            for (Card card : cardArrayList)
                if (card.getMove().equals("WildDraw")) {
                    can = true;
                    break;
                }
            if (!can) {
                int cnt = cardSheet.getUsed();
                cnt = (cnt + 1) * 4;
                System.out.println(color + "received " + cnt + " cards." + ANSI_RESET);
                while (cnt-- > 0)
                    addCard(cardSheet.getNewCard());
                cardSheet.setUsed(-1);
                return 3;
            }

            int row = randomGen.random((cardArrayList.size() + 3) / 4);
            int column = randomGen.random(4);
            if (!valid(row, column))
                return -1;

            Card selected = cardArrayList.get(row * 4 + column);

            if (selected.getMove().equals("WildDraw")) {
                int num = cardSheet.getUsed();
                cardSheet.setOnTop(selected);
                removeCard(selected);
                cardSheet.setUsed(num + 1);
                return topModify(cardSheet, scanner);
            } else
                return -1;
        }

        boolean can = false;
        for (Card card : cardArrayList)
            if (cardSheet.valid(this, card))
                can = true;

        if (!can) {
            Card tmp = cardSheet.getNewCard();
            this.addCard(tmp);
            if (cardSheet.valid(this, tmp)) {
                System.out.println(color + "this Card added and removed :");
                tmp.print();
                cardSheet.setOnTop(tmp);
                removeCard(tmp);
                if (cardSheet.getOnTop().getMove().equals("WildDraw") || cardSheet.getOnTop().getMove().equals("WildCol"))
                    return topModify(cardSheet, scanner);
                return 1 + (tmp.getMove().equals("Reverse") ? 1 : 0);
            }
            System.out.println(color + "This card is added: ");
            tmp.print();
            return 3;
        }

        int row = randomGen.random((cardArrayList.size() + 3) / 4);
        int column = randomGen.random(4);
        if (!valid(row, column))
            return -1;

        Card selected = cardArrayList.get(row * 4 + column);
        if (!cardSheet.valid(this, selected))
            return -1;
        cardSheet.setOnTop(selected);
        removeCard(selected);
        if (cardSheet.getOnTop().getMove().equals("WildDraw") || cardSheet.getOnTop().getMove().equals("WildCol")) {
            return topModify(cardSheet, scanner);
        }
        return 1 + (selected.getMove().equals("Reverse") ? 1 : 0);
    }

    @Override
    protected int topModify(CardSheet cardSheet, Scanner scanner) {
        int inp = randomGen.random(4);
        if (inp == 0)
            cardSheet.getOnTop().setColor(ANSI_YELLOW);
        else if (inp == 1)
            cardSheet.getOnTop().setColor(ANSI_BLUE);
        else if (inp == 2)
            cardSheet.getOnTop().setColor(ANSI_RED);
        else if (inp == 3)
            cardSheet.getOnTop().setColor(ANSI_GREEN);
        return 3;
    }

}

// In The Name Of GOD
public class CardSheet implements Strings {

    private Card[] cards;
    private boolean[] inUse;
    private RandomGen randomGen;
    private Card onTop;
    private int used;
    private int ptr;

    public Card getNewCard() {
        int tmp = randomGen.random(ptr);
        while (inUse[tmp])
            tmp = randomGen.random(ptr);
        inUse[tmp] = true;
        return cards[tmp];
    }

    public CardSheet() {
        randomGen = new RandomGen();
        cards = new Card[108];
        inUse = new boolean[108];
    }

    public void initialize(PlayersManager playersManager) {
        ptr = getPtr(ptr, ANSI_YELLOW);
        ptr = getPtr(ptr, ANSI_BLUE);
        ptr = getPtr(ptr, ANSI_GREEN);
        ptr = getPtr(ptr, ANSI_RED);

        for (int i = 0; i < 4; i++)
            cards[ptr] = new Card(50, ANSI_BLACK, "WildCol", -1, ptr++);

        for (int i = 0; i < 4; i++)
            cards[ptr] = new Card(50, ANSI_BLACK, "WildDraw", -1, ptr++);

        int tmp = randomGen.random(ptr);
        onTop = cards[tmp];
        inUse[tmp] = true;

        if (onTop.getMove().equals("WildDraw")) {
            int rand = randomGen.random(4);
            if (rand == 0)
                onTop.setColor(ANSI_BLUE);
            else if (rand == 1)
                onTop.setColor(ANSI_RED);
            else if (rand == 2)
                onTop.setColor(ANSI_GREEN);
            else
                onTop.setColor(ANSI_YELLOW);
        } else if (onTop.getMove().equals("WildCol")) {
            int rand = randomGen.random(4);
            if (rand == 0)
                onTop.setColor(ANSI_BLUE);
            else if (rand == 1)
                onTop.setColor(ANSI_RED);
            else if (rand == 2)
                onTop.setColor(ANSI_GREEN);
            else
                onTop.setColor(ANSI_YELLOW);
        }

        int numberOfPlayers = playersManager.getNumberOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++)
            for (int j = 0; j < 7; j++)
                playersManager.getPlayer(i).addCard(getNewCard());
    }

    public boolean valid(Player player, Card toBeCheck) {
        if (toBeCheck.getMove().equals("WildDraw")) {
            for (Card card : player.cardArrayList)
                if (!card.getMove().equals("WildDraw") && valid(player, card))
                    return false;
            return true;
        } else if (toBeCheck.getMove().equals("WildCol")) {
            return !(onTop.getMove().equals("WildDraw") && used != -1 || onTop.getMove().equals("Draw2") && used != -1);
        } else if (!toBeCheck.getMove().equals(""))
            return toBeCheck.getMove().equals(onTop.getMove()) ||
                    toBeCheck.getColor().equals(onTop.getColor());
        return toBeCheck.getNumber() == onTop.getNumber() ||
                toBeCheck.getColor().equals(onTop.getColor());
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public Card getOnTop() {
        return onTop;
    }

    public int getUsed() {
        return used;
    }

    public void setOnTop(Card onTop) {
        inUse[onTop.getId()] = false;
        used = 0;
        this.onTop = onTop;
    }

    private int getPtr(int ptr, String color) {
        for (int i = 0; i < 10; i++)
            cards[ptr] = new Card(i, color, "", i, ptr++);
        for (int i = 1; i < 10; i++)
            cards[ptr] = new Card(i, color, "", i, ptr++);
        cards[ptr] = new Card(20, color, "Skip", -1, ptr++);
        cards[ptr] = new Card(20, color, "Reverse", -1, ptr++);
        cards[ptr] = new Card(20, color, "Draw2", -1, ptr++);
        cards[ptr] = new Card(20, color, "Skip", -1, ptr++);
        cards[ptr] = new Card(20, color, "Reverse", -1, ptr++);
        cards[ptr] = new Card(20, color, "Draw2", -1, ptr++);
        return ptr;
    }
}

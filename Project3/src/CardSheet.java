// In The Name Of GOD
public class CardSheet implements Strings {

    private Card[] cards;
    private boolean[] inUse;
    RandomGen randomGen;
    private Card onTop;
    private boolean used;
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
            cards[ptr++] = new Card(50, ANSI_BLACK, "WildCol", -1);

        for (int i = 0; i < 4; i++)
            cards[ptr++] = new Card(50, ANSI_BLACK, "WildDraw", -1);

        int tmp = randomGen.random(ptr);
        onTop = cards[tmp];
        inUse[tmp] = true;

        int numberOfPlayers = playersManager.getNumberOfPlayers();
        for (int i = 0; i < numberOfPlayers; i++)
            for (int j = 0; j < 7; j++) {
                while (inUse[tmp])
                    tmp = randomGen.random(ptr);
                playersManager.getPlayer(i).addCard(cards[tmp]);
                inUse[tmp] = true;
            }
    }

    public boolean valid(Player player, Card toBeCheck) {
        if (toBeCheck.getMove().equals("WildDraw")) {
            for (Card card : player.cardArrayList)
                if (!card.getMove().equals("WildDraw") && valid(player, card))
                    return false;
            return true;
        } else if (toBeCheck.getMove().equals("WildCol")) {
            return true;
        } else if (!toBeCheck.getMove().equals(""))
            return toBeCheck.getMove().equals(onTop.getMove()) ||
                    toBeCheck.getColor().equals(onTop.getColor());
        return toBeCheck.getNumber() == onTop.getNumber() ||
                toBeCheck.getColor().equals(onTop.getColor());
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Card getOnTop() {
        return onTop;
    }

    private int getPtr(int ptr, String color) {
        for (int i = 0; i < 10; i++)
            cards[ptr++] = new Card(i, color, "", i);
        for (int i = 1; i < 10; i++)
            cards[ptr++] = new Card(i, color, "", i);
        cards[ptr++] = new Card(20, color, "Skip", -1);
        cards[ptr++] = new Card(20, color, "Reverse", -1);
        cards[ptr++] = new Card(20, color, "Draw2", -1);
        return ptr;
    }
}

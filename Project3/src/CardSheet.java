// In The Name Of GOD

/**
 * this method manages the card system of the game and is one of the three main class
 * this class can give you a new unUsed card, gives you the last used card of the table and managing the card system.
 */

class CardSheet implements Strings {

    /**
     * cards is an array of cards. the array that keeps the cards in our game
     * inUse[i] := if it's true then it means card[i] is in hand of someone or is the onTop card.
     * randomGen generates randoms.
     * onTop is the card that is on the table and we should see it to do some of our moves
     * used is an integer for the number of +2 or +4 that are at the table and waiting for a victim.
     * if there's +2 || +4 and a player has them then this int will increase by one otherwise it will give him the number of cards and it'll set to -1 means that the cards on the table are used.
     * used has already been used for skipping the player.
     * usage of these number is visible in move method and valid method.
     * ptr is the number of cards(ptr stands for pointer because of i use it when I'm generating the cards.)
     */
    private Card[] cards;
    private boolean[] inUse;
    private RandomGen randomGen;
    private Card onTop;
    private int used;
    private int ptr;

    /**
     * @return a new free card and set it's statues as used.
     */
    Card getNewCard() {
        int tmp = randomGen.random(ptr);
        while (inUse[tmp])
            tmp = randomGen.random(ptr);
        inUse[tmp] = true;
        return cards[tmp];
    }

    /**
     * the constructor of the class
     */
    CardSheet() {
        randomGen = new RandomGen();
        cards = new Card[108];
        inUse = new boolean[108];
    }

    /**
     * this will initialize the table by creating the cards and giving 7 unique cards to each player and making things ready to start the game
     * @param playersManager is the player manager system to giving each player its cards.
     */
    void initialize(PlayersManager playersManager) {
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

        if (onTop.getMove().equals("WildDraw") || onTop.getMove().equals("WildCol")) {
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


    /**
     * this method is for checking that if it's valid to do a move or not !
     * @param player is the player that wants to put the card
     * @param toBeCheck is the card that wants to get on top
     * @return true if we player can put toBeCheck on the top of the deck
     */
    public boolean valid(Player player, Card toBeCheck) {
        if (toBeCheck.getMove().equals("WildDraw")) { // by checking that other cards aren't valid to do
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


    /**
     *
     * @param used is the new number for used field
     */
    void setUsed(int used) {
        this.used = used;
    }

    /**
     *
     * @return onTop card of the game
     */
    Card getOnTop() {
        return onTop;
    }

    /**
     *
     * @return the used number
     */
    int getUsed() {
        return used;
    }

    /**
     * this method replaces onTop card.
     * @param onTop is a new onTop card
     */
    void setOnTop(Card onTop) {
        inUse[this.onTop.getId()] = false;
        used = 0;
        this.onTop = onTop;
    }

    /**
     * this method creates the cards with Color, color and starts to add them from the index ptr in our card array.
     * @param ptr is a current free space in our array of cards.
     * @param color is the color of the set that we're adding(Blue,Red,...)
     * @return ptr after adding the cards.
     */
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

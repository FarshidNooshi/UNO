// In The Name Of GOD

/**
 * This class explains a simple card information and stands for a card in real world.
 * A real card has a score that give to his owner at the end of the game
 * a color that in here it's a string for coloring the console while printing the card
 * a move or action that can be "Reverse", "WildCol", and etc.
 * and it can have a number.
 * id is a unique number for each card that used for better knowing one appropriate card.(used for comparing cards and storing them)
 */

class Card implements Strings {
    private int score, id;
    private String color, move;
    private int number;

    /**
     * different parameters can be empty like for some cards as this class is a general class for the game cards.
     * for example the move field is "" or for someone their numbers is -1.
     *
     * @param sc     is the score of the card
     * @param color  is the color of the card
     * @param move   is the move of the card
     * @param number is the number of the card
     * @param id     is a unique number between 0 to 107 for each card
     */
    Card(int sc, String color, String move, int number, int id) {
        this.id = id;
        score = sc;
        this.color = color;
        this.number = number;
        this.move = move;
    }

    /**
     * this method simply prints a single card in the console with its initial color.
     */
    void print() {
        System.out.println(color + "|$$$$$$$$$$$$$$$|");
        System.out.println("|               |");
        if (move.equals(""))
            System.out.println("|       " + number + "       |");
        else {
            System.out.print("|    " + move);
            for (int i = 4 + move.length(); i < 15; i++)
                System.out.print(" ");
            System.out.println("|");
        }
        System.out.println("|               |");
        System.out.println("|$$$$$$$$$$$$$$$|" + ANSI_RESET);
    }

    /**
     * @return the number of the card
     */

    int getNumber() {
        return number;
    }

    /**
     *
     * @return color of the card
     */
    String getColor() {
        return color;
    }

    /**
     *
     * @return the score of the card
     */
    int getScore() {
        return score;
    }

    /**
     *
     * @return the move of the card
     */
    String getMove() {
        return move;
    }

    /**
     *
     * @return the id of the card
     */
    public int getId() {
        return id;
    }

    /**
     * id is used in here .
     * one of it's main reasons is to checking equality with a simple "==" .
     * @param obj to be checked for equality
     * @return true if obj and this are uqual
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;
        Card check = (Card) obj;
        return check.getId() == this.id;
    }

    void setColor(String color) {
        this.color = color;
    }
}

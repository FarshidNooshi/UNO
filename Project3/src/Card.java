// In The Name Of GOD
public class Card implements Strings {
    private int score;
    private String color, move;
    private int number;

    public Card(int sc, String color, String move, int number) {
        score = sc;
        this.color = color;
        this.number = number;
        this.move = move;
    }

    public void print() {
        System.out.println(color + "|€€€€€€€€€€€€€€€|");
        System.out.println("|               |");
        if (!move.equals(""))
            System.out.println("|      " + number + "        |");
        else {
            System.out.println("|      " + move);
            for (int i = 6 + move.length(); i < 15; i++)
                System.out.print(" ");
            System.out.println("|");
        }
        System.out.println("|               |");
        System.out.println("|€€€€€€€€€€€€€€€|" + ANSI_RESET);
    }

    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public int getScore() {
        return score;
    }

    public String getMove() {
        return move;
    }
}

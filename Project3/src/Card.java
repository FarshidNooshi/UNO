// In The Name Of GOD
class Card implements Strings {
    private int score, id;
    private String color, move;
    private int number;

    Card(int sc, String color, String move, int number, int id) {
        this.id = id;
        score = sc;
        this.color = color;
        this.number = number;
        this.move = move;
    }

    void print() {
        System.out.println(color + "|€€€€€€€€€€€€€€€|");
        System.out.println("|               |");
        if (move.equals(""))
            System.out.println("|      " + number + "        |");
        else {
            System.out.print("|      " + move);
            for (int i = 6 + move.length(); i < 15; i++)
                System.out.print(" ");
            System.out.println("|");
        }
        System.out.println("|               |");
        System.out.println("|€€€€€€€€€€€€€€€|" + ANSI_RESET);
    }

    int getNumber() {
        return number;
    }

    String getColor() {
        return color;
    }

    int getScore() {
        return score;
    }

    String getMove() {
        return move;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;
        Card check = (Card)obj;
        return check.getId() == this.id;
    }

    void setColor(String color) {
        this.color = color;
    }
}

// In The Name Of GOD

import java.util.Scanner;

/**
 * last but not least...
 * this the our third most important method of the game that controls and manages the players movements and actions or it prints and ...
 * this class is built for better managing and working with players.
 */

class PlayersManager implements Strings {
    private Scanner scanner; // initial scanner of the game
    private Player[] players; // players arraylist
    private int direction; // 1 -> clockWise, (numberOfPlayers - 1) -> counterClockWise
    private int numberOfPlayers;
    private int currentTurn;
    private boolean[] isComputer; // if true means the user is computer

    /**
     * constructor
     * @param numberOfPlayers is the number of players in the game
     * @param numberOfComputers is the number of computer players in the game
     * @param scanner is the initial scanner in the game
     */
    PlayersManager(int numberOfPlayers, int numberOfComputers, Scanner scanner) {
        this.scanner = scanner;
        this.numberOfPlayers = numberOfPlayers;
        players = new Player[numberOfPlayers];
        isComputer = new boolean[numberOfPlayers];
        RandomGen randomGen = new RandomGen();
        currentTurn = 0;
        direction = 1;

        for (int i = 0, tmp = randomGen.random(numberOfPlayers); i < numberOfComputers; i++) {
            while (isComputer[tmp])
                tmp = randomGen.random(numberOfPlayers);
            isComputer[tmp] = true;
        }

        for (int i = 0; i < numberOfPlayers; i++)
            players[i] = isComputer[i] ? new Computer(i) : new Human(i);
    }

    /**
     *
     * @param idx is a index
     * @return the player with id = idx
     */
    Player getPlayer(int idx) {
        return players[idx];
    }

    /**
     *
     * @return nomberOfPlayers
     */
    int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * reseting the screen by printing new lines
     */
    private void clrScreen() {
        for (int i = 0; i < 25; i++)
            System.out.println();
    }

    /**
     * prints the direction of the table using characters and number of cards for each player with the players color
     */
    private void print() {
        if (direction == 1)
            System.out.println(ANSI_BLUE + "Direction of the table is " + clockwise + ANSI_RESET);
        else
            System.out.println(ANSI_RED + "Direction of the table is " + counterClockwise + ANSI_RESET);
        for (int i = 0; i < numberOfPlayers; i++)
            System.out.println(players[i].color + "Player " + (i + 1) + " has " + players[i].cardArrayList.size() + " Cards." + ANSI_RESET);
    }

    /**
     * this method does one step in the game and does one action.
     * @param cardSheet is the cardSheet of the game
     * @throws InterruptedException is here because of intellij and throws an exception
     */
    void go(CardSheet cardSheet) throws InterruptedException {
        Thread.sleep(3000);
        clrScreen();
        print();
        System.out.print(players[currentTurn].color + "Player " + (currentTurn + 1) + " should move\n" + ANSI_RESET);
        if (isComputer[currentTurn]) {
            cardSheet.getOnTop().print();
            players[currentTurn].printUserCards();
        }
        int tmp = players[currentTurn].move(scanner, cardSheet);
        while (tmp == -1) {
            if (isComputer[currentTurn]) {
                tmp = players[currentTurn].move(scanner, cardSheet);
                continue;
            }
            Thread.sleep(3000);
            clrScreen();
            print();
            System.out.print(players[currentTurn].color + "Player " + (currentTurn + 1) + " should move again\n" + ANSI_RESET);
            tmp = players[currentTurn].move(scanner, cardSheet);
        }

        if (tmp == 2) {
            direction = (direction == 1 ? numberOfPlayers - 1 : 1);
            cardSheet.setUsed(-1);
        }

        currentTurn += direction;
        currentTurn %= numberOfPlayers;
    }

    /**
     * direction = 1 -> clockWise
     * direction = numberOfPlayers - 1 -> counterClockWise
     * @param direction is the new direction in the table(clockWise or counterClockWise
     */
    void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     *
     * @return true if the game is over
     */
    boolean finish() {
        for (int i = 0; i < numberOfPlayers; i++)
            if (players[i].cardArrayList.isEmpty())
                return true;
        return false;
    }

    /**
     * sorting players according to their scores.
     */
    void finished() {
        for (int i = 0; i < numberOfPlayers; i++)
            for (int j = i + 1; j < numberOfPlayers; j++)
                if (players[i].score > players[j].score) {
                    Player tmp = players[i];
                    players[i] = players[j];
                    players[j] = tmp;
                }
    }

}

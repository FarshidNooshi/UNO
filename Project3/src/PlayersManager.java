// In The Name Of GOD
import java.util.Scanner;

public class PlayersManager implements Strings {
    private RandomGen randomGen;
    private Scanner scanner;
    private Player[] players;
    private int direction; // 1 -> clockWise, (numberOfPlayers - 1) -> counterClockWise
    private int numberOfComputers, numberOfPlayers, currentTurn;
    private boolean[] isComputer;

    public PlayersManager(int numberOfPlayers, int numberOfComputers, Scanner scanner) {
        this.scanner = scanner;
        this.numberOfComputers = numberOfComputers;
        this.numberOfPlayers = numberOfPlayers;
        players = new Player[numberOfPlayers];
        isComputer = new boolean[numberOfPlayers];
        randomGen = new RandomGen();
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

    public Player getPlayer(int idx) {
        return players[idx];
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    private void clrScreen() {
        for (int i = 0; i < 25; i++)
            System.out.println();
    }

    public void print() {
        if (direction == 1)
            System.out.println(ANSI_BLUE + "Direction of the table is " + clockwise + ANSI_RESET);
        else
            System.out.println(ANSI_RED + "Direction of the table is " + counterClockwise + ANSI_RESET);
        for (int i = 0; i < numberOfPlayers; i++)
            System.out.println(players[i].color + "Player " + (i + 1) + " has " + players[i].cardArrayList.size() + " Cards." + ANSI_RESET);
    }

    public void go(CardSheet cardSheet) throws InterruptedException {
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

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean finish() {
        for (int i = 0; i < numberOfPlayers; i++)
            if (players[i].score == 0)
                return true;
        return false;
    }

    public void finished() {
        for (int i = 0; i < numberOfPlayers; i++)
            for (int j = i + 1; j < numberOfPlayers; j++)
                if (players[i].score > players[j].score) {
                    Player tmp = players[i];
                    players[i] = players[j];
                    players[j] = tmp;
                }
    }

}

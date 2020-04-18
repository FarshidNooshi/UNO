// In The Name Of GOD
import java.util.Scanner;

public class Game implements Strings {
    private Scanner scanner;
    private PlayersManager playersManager;
    private CardSheet cardSheet;

    public Game() {
        scanner = new Scanner(System.in);
        cardSheet = new CardSheet();
    }

    void start() throws InterruptedException {
        System.out.print("Please enter number of players: \nplease enter number of players to be computer: ");
        int numberOfPlayers = scanner.nextInt();
        int numberOfComputers = scanner.nextInt();
        System.out.println();

        while (numberOfComputers > numberOfPlayers || numberOfPlayers > 8) {
            System.out.print("Please enter 2 new numbers for total number of players and computers: ");
            numberOfPlayers = scanner.nextInt();
            numberOfComputers = scanner.nextInt();
            System.out.println();
        }

        playersManager = new PlayersManager(numberOfPlayers, numberOfComputers, scanner);
        cardSheet.initialize(playersManager);
        while (!playersManager.finish())
            playersManager.go(cardSheet);
        endGame();
    }

    private void endGame() {
        playersManager.finish();

        for (int i = 0; i < playersManager.getNumberOfPlayers(); i++)
            playersManager.getPlayer(i).print(i);
        System.out.println("Hope to see you next time!");
    }

}

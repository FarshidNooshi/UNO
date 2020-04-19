// In The Name Of GOD

import java.util.Scanner;

/**
 * this class is the body of the game
 * it represents the steps of which the game continues
 * as you can read, it controls two main classes for managing the cards sheet and players manager
 */

class Game implements Strings {
    private Scanner scanner; // a unique scanner for the whole program
    private PlayersManager playersManager;
    private CardSheet cardSheet;

    /**
     * constructor
     */
    Game() {
        scanner = new Scanner(System.in);
        cardSheet = new CardSheet();
    }

    /**
     *
     * this method starts the game and continues to run until the game finishes.
     * first we ask about the number of players. for color limits, number of players is limited to at most 8 players at the moment
     * we need to control two other classes for doing the game one of them will control the card system of the game (which one is free, which one is at the top and ...)
     * and other one will control the players system and controls their actions and plays. so playersManager is for managing players and improving the readability of the program
     * and cardSheet is the class for managing cards.
     * @throws InterruptedException it is here because of intellij
     */
    void start() throws InterruptedException {
        System.out.print("Please enter number of players: ");
        int numberOfPlayers = scanner.nextInt();
        System.out.print("\nplease enter number of players to be computer: ");
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

        if (cardSheet.getOnTop().getMove().equals("Reverse"))
            playersManager.setDirection(numberOfPlayers - 1);

        while (!playersManager.finish())
            playersManager.go(cardSheet);
        endGame();
    }

    private void endGame() {
        playersManager.finished();

        for (int i = 0; i < playersManager.getNumberOfPlayers(); i++)
            playersManager.getPlayer(i).print(i);
        System.out.println(ANSI_PURPLE + "Hope to see you next time!" + ANSI_RESET);
    }

}

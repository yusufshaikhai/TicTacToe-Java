import controllers.GameController;
import exceptions.DuplicateSymbolException;
import exceptions.MoreThanOneException;
import exceptions.PlayerAndBoardCountMismatch;
import models.*;
import strategy.ColWinningStrategy;
import strategy.DiagonalWinningStrategy;
import strategy.RowWinningStrategy;
import strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws DuplicateSymbolException, PlayerAndBoardCountMismatch, MoreThanOneException {
        Scanner scanner = new Scanner(System.in);
        int dimension = 3;

        ArrayList<Player> players = new ArrayList<>();
        players.add(new HumanPlayer('X', "Khadija", 1, PlayerType.HUMAN, scanner));
        players.add(new BotPlayer('O', "Llama", 2, PlayerType.BOT, DifficultyLevel.EASY));

        ArrayList<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new ColWinningStrategy());
        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        GameController gameController = new GameController();
        Game game = gameController.createGame(dimension, players, winningStrategies);

        while(game.getGameState().equals(GameState.IN_PROGRESS)){
            gameController.printBoard(game);
            gameController.makeMove(game);
        }

        gameController.printBoard(game);

        if(game.getGameState().equals(GameState.CONCLUDED)){
            System.out.println(game.getWinner().getName() + " won the game");
        }

        if(game.getGameState().equals(GameState.DRAW)){
            System.out.println("It is a draw.");
        }

    }
}
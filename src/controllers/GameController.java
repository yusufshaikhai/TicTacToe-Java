package controllers;

import exceptions.DuplicateSymbolException;
import exceptions.MoreThanOneException;
import exceptions.PlayerAndBoardCountMismatch;
import models.Game;
import models.Player;
import strategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game createGame(int dimension, List<Player> players, List<WinningStrategy> winningStrategies)
            throws DuplicateSymbolException, PlayerAndBoardCountMismatch, MoreThanOneException {
        return Game.getBuilder().setDimension(dimension).setPlayers(players).setWinningStrategy(winningStrategies).build();
    }

    public void printBoard(Game game){
        game.printBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }
}

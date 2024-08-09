package models;

import factories.BotPlayingStrategyFactory;
import strategy.BotPlayingStrategy;

public class BotPlayer extends Player{
    private DifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public BotPlayer(Character symbol, String name, int id, PlayerType playerType, DifficultyLevel difficultyLevel) {
        super(symbol, name, id, playerType);
        this.difficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(difficultyLevel);
    }

    @Override
    public Cell makeMove(Board board) {

        System.out.println("Bots turn to play");
        Cell cell = botPlayingStrategy.makeMove(board);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;
    }
}

package models;

import exceptions.DuplicateSymbolException;
import exceptions.MoreThanOneException;
import exceptions.PlayerAndBoardCountMismatch;
import strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

public class Game {
    private List<Player> players;
    private Board board;
    private GameState gameState;
    private Player winner;
    private int nextPlayerIndex;
    private List<Move> moves;
    private List<WinningStrategy> winningStrategies;


    private Game(List<Player> players, int dimension, List<WinningStrategy> winningStrategy) {
        this.players = players;
        this.board = new Board(dimension);
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.moves = new ArrayList<>();
        this.winningStrategies = winningStrategy;
    }

    public static GameBuilder getBuilder(){
        return new GameBuilder();
    }

    public void printBoard() {
        this.board.printBoard();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public void makeMove() {
        Player player = players.get(nextPlayerIndex);
        Cell cell = player.makeMove(board);
        Move move = new Move(player, cell);
        moves.add(move);

        if(checkWinner(move)){
            winner = player;
            gameState = GameState.CONCLUDED;
            return;
        }
        if(moves.size() == (board.getDimension()*board.getDimension())){
            gameState = GameState.DRAW;
            return;
        }

        nextPlayerIndex++;
        nextPlayerIndex = nextPlayerIndex % players.size();

    }

    private boolean checkWinner(Move move) {
        for (WinningStrategy winningStrategy: winningStrategies) {
            if(winningStrategy.checkWinner(board, move)) {
                return true;
            }
        }
        return false;
    }

    public static class GameBuilder{
        private List<Player> players;
        private int dimension;
        private List<WinningStrategy> winningStrategies;

        private GameBuilder(){
            dimension = 0;
            this.players = new ArrayList<>();
        }

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public GameBuilder setWinningStrategy(List<WinningStrategy> winningStrategies){
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Game build() throws MoreThanOneException, PlayerAndBoardCountMismatch, DuplicateSymbolException {
            validateUniqueSymbolForPlayers();
            validateBotCount();
            validateDimensionAndPlayerCount();
            return new Game(players, dimension, winningStrategies);
        }

        private void validateDimensionAndPlayerCount() throws PlayerAndBoardCountMismatch {
            if(this.players.size()!=(dimension-1))
                throw new PlayerAndBoardCountMismatch();
        }

        private void validateBotCount() throws MoreThanOneException {
            int botCount = 0;
            for(Player player: players){
                if(player.getPlayerType() == PlayerType.BOT)
                    botCount++;
                if(botCount>1)
                    throw new MoreThanOneException();
            }
        }

        private void validateUniqueSymbolForPlayers() throws DuplicateSymbolException {
            HashSet<Character> symbol = new HashSet<>();
            for(Player player: players){
                if(symbol.contains(player.getSymbol()))
                    throw new DuplicateSymbolException();
                symbol.add(player.getSymbol());
            }
        }
    }
}

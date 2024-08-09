package strategy;

import models.Board;
import models.Move;

import java.util.HashMap;
import java.util.Map;

public class ColWinningStrategy implements WinningStrategy {
    private final Map<Integer, Map<Character, Integer>> colMaps = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Character symbol = move.getPlayer().getSymbol();

        if(!colMaps.containsKey(col)){
            colMaps.put(col, new HashMap<>());
        }
        Map<Character, Integer> colMap = colMaps.get(col);
        if(!colMap.containsKey(symbol)){
            colMap.put(symbol, 0);
        }
        colMap.put(symbol, colMap.get(symbol)+1);
        return colMap.get(symbol).equals(board.getDimension());
    }
}

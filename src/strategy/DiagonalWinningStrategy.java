package strategy;

import models.Board;
import models.Move;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy {
    private final HashMap<Character, Integer> leftDaiMap = new HashMap<>();
    private final HashMap<Character, Integer> rightDaiMap = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Character symbol = move.getPlayer().getSymbol();
        if(row==col){
            if(!leftDaiMap.containsKey(symbol)){
                leftDaiMap.put(symbol, 0);
            }
            leftDaiMap.put(symbol, leftDaiMap.get(symbol)+1);
            if(leftDaiMap.get(symbol).equals(board.getDimension())){
                return true;
            }
        }

        if((row+col)==(board.getDimension()-1)){
            if(!rightDaiMap.containsKey(symbol)){
                rightDaiMap.put(symbol, 0);
            }
            rightDaiMap.put(symbol, rightDaiMap.get(symbol)+1);
            return rightDaiMap.get(symbol).equals(board.getDimension());
        }
        return false;
    }
}

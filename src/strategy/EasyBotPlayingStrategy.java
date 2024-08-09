package strategy;

import models.Board;
import models.Cell;
import models.CellState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {


    @Override
    public Cell makeMove(Board board) {
//        for (int i = 0; i < board.getDimension(); ++i) {
//            for(int j = 0; j<board.getDimension(); ++j){
//                if(board.getBoard().get(i).get(j).getCellState().equals(CellState.EMPTY)){
//                    return board.getBoard().get(i).get(j);
//                }
//
//            }
//        }
        for(List<Cell> row: board.getBoard()){
            for(Cell cell: row){
                if(CellState.EMPTY.equals(cell.getCellState())){
                    return cell;
                }
            }
        }

        return null;
    }
}

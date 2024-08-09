package models;

import java.util.Scanner;

public class HumanPlayer extends Player{
    private Scanner scanner;
//    public HumanPlayer(Character symbol, String name, int id, PlayerType playerType) {
//        super(symbol, name, id, playerType);
//    }

    public HumanPlayer(Character symbol, String name, int id, PlayerType playerType, Scanner scanner) {
        super(symbol, name, id, playerType);
        this.scanner = scanner;
    }

    @Override
    public Cell makeMove(Board board) {
        System.out.print(this.getName() + " Its your turn, enter row and col you want to play");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        while(!validateRowAndCol(row, col, board)) {
            System.out.print(this.getName() + " Invalid move kindly add it again.");
            row = scanner.nextInt();
            col = scanner.nextInt();
        }

        Cell cell = board.getBoard().get(row).get(col);
        cell.setCellState(CellState.FILLED);
        cell.setPlayer(this);
        return cell;

    }

    private boolean validateRowAndCol(int row, int col, Board board) {
        if(row>=board.getDimension()){
            return false;
        }
        if (col >= board.getDimension()){
            return false;
        }
        if(CellState.FILLED.equals(board.getBoard().get(row).get(col).getCellState())){
            return false;
        }
        return true;
    }

}

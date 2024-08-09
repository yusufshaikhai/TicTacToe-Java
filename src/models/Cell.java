package models;

public class Cell {
    int row;
    int col;
    private CellState cellState;
    private Player player;

    public CellState getCellState() {
        return cellState;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
    }

    public void printCell() {
        if(cellState.equals(CellState.FILLED)){
            System.out.print("| " + player.getSymbol() + " |");
        }else{
            System.out.print("| - |");
        }
    }
}

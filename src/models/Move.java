package models;

public class Move {
    private Player player;
    private Cell cell;
    private Character symbol;

    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
        this.symbol = player.getSymbol();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }
}

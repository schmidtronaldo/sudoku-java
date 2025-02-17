package game.model;

public class Cell {
    private int row;
    private int col;
    private int value;
    private boolean isEditable;

    public Cell(int row, int col, int value, boolean isEditable) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.isEditable = isEditable;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (isEditable) {
            this.value = value;
        }
    }

    public boolean isEditable() {
        return isEditable;
    }
}

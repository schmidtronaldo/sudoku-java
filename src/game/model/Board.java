package game.model;

import java.util.Random;

public class Board {
    private static final int SIZE = 9;
    private static final int EMPTY = 0;
    private Cell[][] cells;

    public Board() {
        cells = new Cell[SIZE][SIZE];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                cells[i][j] = new Cell(i, j, EMPTY, true);
            }
        }
    }

    public void generateBoard() {
        generateBoard(0, 0);
    }

    private boolean generateBoard(int row, int col) {
        if (row == SIZE) {
            return true;
        }
        if (col == SIZE) {
            return generateBoard(row + 1, 0);
        }
        if (cells[row][col].getValue() != EMPTY) {
            return generateBoard(row, col + 1);
        }

        for (int num = 1; num <= SIZE; num++) {
            if (isValidMove(row, col, num)) {
                cells[row][col].setValue(num);

                if (generateBoard(row, col + 1)) {
                    return true;
                }

                cells[row][col].setValue(EMPTY);
            }
        }

        return false;
    }

    public void removeNumbers(int numbersToRemove) {
        Random rand = new Random();
        while (numbersToRemove > 0) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);

            if (cells[row][col].getValue() != EMPTY) {
                cells[row][col].setValue(EMPTY);
                numbersToRemove--;
            }
        }
    }

    public boolean isValidMove(int row, int col, int num) {
        // Verificar linha e coluna
        for (int i = 0; i < SIZE; i++) {
            if (cells[row][i].getValue() == num || cells[i][col].getValue() == num) {
                return false;
            }
        }

        // Verificar bloco 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (cells[i][j].getValue() == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isGameComplete() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (cells[i][j].getValue() == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
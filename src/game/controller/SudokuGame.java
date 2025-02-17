package game.controller;

import game.model.Board;
import game.view.GameView;

public class SudokuGame {
    private Board board;
    private GameView view;

    public SudokuGame() {
        board = new Board();
        view = new GameView();
    }

    public void startGame() {
        board.generateBoard();
        board.removeNumbers(40); // Remover 40 números para criar o puzzle
        view.displayBoard(board.getCells());

        while (!board.isGameComplete()) {
           
            int[] move = view.getUserMove();
            int row = move[0], col = move[1], num = move[2];

            if (board.isValidMove(row, col, num)) {
                board.getCells()[row][col].setValue(num);
                view.displayBoard(board.getCells());
            } else {
                view.displayInvalidMove();
            }
        }

        view.displayGameComplete();
    }
}
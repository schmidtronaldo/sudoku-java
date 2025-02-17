package game.view;

import game.model.Cell;
import game.util.InputValidator;
import java.util.Scanner;

public class GameView {
    private Scanner scanner;

    public GameView() {
        scanner = new Scanner(System.in);
    }

    public void displayBoard(Cell[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                System.out.print(cells[i][j].getValue() == 0 ? "." : cells[i][j].getValue());
                System.out.print(" ");
            }
            System.out.println();
        }
    }


    public int[] getUserMove() {
        return InputValidator.getUserMove(scanner);
    }

    public void displayInvalidMove() {
        System.out.println("Movimento inválido. Tente novamente.");
    }

    public void displayGameComplete() {
        System.out.println("Parabéns! Você completou o Sudoku!");
    }
}
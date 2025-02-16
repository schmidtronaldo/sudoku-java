import java.util.Random;
import java.util.Scanner;

public class Main {

    private static final int SIZE = 9;
    private static final int EMPTY = 0;
    private static final int[][] board = new int[SIZE][SIZE];

    public static void main(String[] args) {
        generateBoard();
        removeNumbers();
        printBoard();

        playGame();
    }

    // Gerar um tabuleiro de Sudoku válido
    private static boolean generateBoard(int row, int col) {
        if (row == SIZE) {
            return true;
        }
    
        if (col == SIZE) {
            return generateBoard(row + 1, 0);
        }
    
        if (board[row][col] != EMPTY) {
            return generateBoard(row, col + 1);
        }
    
        for (int num = 1; num <= SIZE; num++) {
            if (isValidMove(row, col, num)) {
                board[row][col] = num;
    
                if (generateBoard(row, col + 1)) {
                    return true;
                }
    
                board[row][col] = EMPTY;
            }
        }
    
        return false;
    }
    private static void generateBoard() {
        generateBoard(0, 0);
    }

    // Remover alguns números para criar o puzzle
    private static void removeNumbers() {
        Random rand = new Random();
        int numbersToRemove = 40; // Ajuste a dificuldade
    
        while (numbersToRemove > 0) {
            int row = rand.nextInt(SIZE);
            int col = rand.nextInt(SIZE);
    
            if (board[row][col] != EMPTY) {
                board[row][col] = EMPTY;
                numbersToRemove--;
            }
        }
    }

    // Exibir o tabuleiro no terminal
    private static void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] == EMPTY ? "." : board[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    // Permitir que o usuário insira números
    private static void playGame() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!isGameComplete()) {
                System.out.println("Digite a linha, coluna e número (ex: 1 2 3): ");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;
                int num = scanner.nextInt();
                
                if (isValidMove(row, col, num)) {
                    board[row][col] = num;
                    printBoard();
                } else {
                    System.out.println("Movimento inválido. Tente novamente.");
                }
            }
            System.out.println("Parabéns! Você completou o Sudoku!");
        }
    }

    // Verificar se o movimento é válido
    private static boolean isValidMove(int row, int col, int num) {
        // Verificar linha e coluna
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // Verificar o bloco 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    // Verificar se o jogo foi concluído corretamente
    private static boolean isGameComplete() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    
    
  
}
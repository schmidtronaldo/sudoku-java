package game.util;

import java.util.Scanner;

public class InputValidator {

    public static int[] getUserMove(Scanner scanner) {
        while (true) {
            System.out.println("Digite a linha, coluna e número (ex: 1 2 3 ou 123): ");
            String input = scanner.nextLine().trim();

            // Verifica se a entrada tem 3 dígitos (ex: "123")
            if (input.length() == 3 && input.matches("\\d{3}")) {
                int row = Character.getNumericValue(input.charAt(0)) - 1;
                int col = Character.getNumericValue(input.charAt(1)) - 1;
                int num = Character.getNumericValue(input.charAt(2));
                return new int[]{row, col, num};
            }
            // Verifica se a entrada tem 3 números separados por espaços (ex: "1 2 3")
            else if (input.matches("\\d \\d \\d")) {
                String[] parts = input.split(" ");
                int row = Integer.parseInt(parts[0]) - 1;
                int col = Integer.parseInt(parts[1]) - 1;
                int num = Integer.parseInt(parts[2]);
                return new int[]{row, col, num};
            }
            // Entrada inválida
            else {
                System.out.println("Entrada inválida. Use o formato '1 2 3' ou '123'.");
            }
        }
    }
}
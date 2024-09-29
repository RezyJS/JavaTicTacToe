import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в Крестики-Нолики!");
        while (true) {
            game(scanner);

            System.out.println();
            System.out.println("Напишите 'выход' чтобы закончить играть!");
            String wannaContinue = scanner.nextLine();

            if (wannaContinue.equals("выход")) {
                break;
            }
        }
    }

    static void game(Scanner sc) {
        char[][] field = { {' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '} };

        System.out.println();

        System.out.println("Ходят Х");

        System.out.println();

        printField(field);

        System.out.println();

        boolean win = false;
        char player = 'X';

        while (!win) {
            makeMove(player, field, sc);
            win = checkIfSomeoneWon(player, field);
            if (win) {
                System.out.println();
                printField(field);
                break;
            }
            if (player == 'X') player = '0';
            else player = 'X';
            printField(field);
            System.out.println();
            System.out.println("Ходят " + player);
        }

        System.out.println();

        if (player == 'X') {
            System.out.println("Победили Х");
        } else {
            System.out.println("Победили 0");
        }
    }

    static void printField(char[][] field) {
        for (char[] line: field) {
            System.out.print("| ");
            for (int i = 0; i < 3; i++) {
                System.out.print(line[i] + " | ");
            }
            System.out.println();
        }
    }

    static void makeMove(char player, char[][] arr, Scanner sc) {
        byte x = 0, y = 0;
        while (x < 1 || x > 3 || y < 1 || y > 3) {
            x = sc.nextByte();
            y = sc.nextByte();
        }
        if (player == 'X') {
            arr[x - 1][y - 1] = 'X';
        } else {
            arr[x - 1][y - 1] = '0';
        }
    }

    static boolean checkIfSomeoneWon(char player, char[][] field) {
        // check every line
        int cnt = 0;
        for (char[] line: field) {
            for (char c: line) {
                if (c == player) ++cnt;
            }
            if (cnt == 3) return true;
            else cnt = 0;
        }

        // check every column
        for (byte i = 0; i < 3; ++i) {
            for (byte j = 0; j < 3; ++j) {
                if (field[j][i] == player) ++cnt;
            }
            if (cnt == 3) return true;
            else cnt = 0;
        }

        // check main diagonal
        if (field[0][0] == field[1][1] && field[1][1] == field[2][2] && field[0][0] == player) return true;

        // check second diagonal
        if (field[0][2] == field[1][1] && field[1][1] == field[2][0] && field[0][2] == player) return true;

        return false;
    }
}
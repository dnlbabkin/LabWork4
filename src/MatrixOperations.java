import java.io.*;
import java.util.Scanner;

public class MatrixOperations {

    private static int[][] A;
    private static int[] X;

    public static void showMatrix() {
        A = choiceMatrixForUser();

        System.out.println("Исходная матрица:");
        for (int[] ints : A) {
            for (int j = 0; j < A.length; j++) {
                System.out.print(ints[j] + "\t");
            }

            System.out.println();
        }
    }

    public static void showResultArray() {
        X = matrixOperation(A);

        System.out.println("Результат:");
        for (int x : X) {
            System.out.println(x);
        }
    }

    public static void writeResultArray() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("output.txt"));

            for (int x : X) {
                writer.write(String.valueOf(x));
                writer.write(" ");
                writer.write("\r\n");
            }
                writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int[][] enterMatrixFromKeyboard() {
        Scanner array = new Scanner(System.in);
        Scanner range = new Scanner(System.in);

        System.out.println("Введите размер матрицы: ");
        int n = range.nextInt();

        A = new int[n][n];

        System.out.println("Введите элементы матрицы: ");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                A[i][j] = array.nextInt();
            }
        }
        return A;
    }

    private static int[][] enterMatrixFromFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("matrix.txt"));
        int n = scanner.nextInt();
        int[][] x = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                x[i][j] = scanner.nextInt();
            }
        }

        return x;
    }

    private static int[] matrixOperation(int[][] A) {
        int[] X = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            boolean containsRequiredNumber = false;
            for (int j = 0; j < A.length; j++) {
                if (A[i][j] > -1) {
                    containsRequiredNumber = true;
                    break;
                }
            }

            if (containsRequiredNumber) {
                int sum = 0;
                for (int j = 0; j < A.length; j++) {
                    sum += A[i][j];
                }

                X[i] = sum;
            }
        }
        return X;
    }

    private static int[][] choiceMatrixForUser() {
        Scanner choice = new Scanner(System.in);
        System.out.println("Выберите откуда вводить матрицу: \n" +
                "1. Клавиатура;\n" +
                "2. Файл.");
        int userChoice = choice.nextInt();

        if (userChoice == 1) {
            A = enterMatrixFromKeyboard();
        } else {
            try {
                A = enterMatrixFromFile();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return A;
    }
}

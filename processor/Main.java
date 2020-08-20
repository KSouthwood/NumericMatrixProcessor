package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        menu(input);
    }

    private static void menu(Scanner input) {
        int choice;

        do {
            System.out.println("1. Add matrices");
            System.out.println("2. Multiply matrix to a constant");
            System.out.println("3. Multiply matrices");
            System.out.println("4. Transpose matrix");
            System.out.println("5. Calculate a determinant");
            System.out.println("6. Inverse matrix");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    addMatrices(input);
                    break;
                case 2:
                    multiplyMatrixByConstant(input);
                    break;
                case 3:
                    multiplyMatrixByMatrix(input);
                    break;
                case 4:
                    transposeMenu(input);
                    break;
                case 5:
                    findMatrixDeterminant(input);
                    break;
                case 6:
                    findMatrixInverse(input);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Unknown entry.");
                    break;
            }
        } while(true);
    }

    private static void transposeMenu(Scanner input) {
        int choice;

        do {
            System.out.println();
            System.out.println("1. Main diagonal");
            System.out.println("2. Side diagonal");
            System.out.println("3. Vertical line");
            System.out.println("4. Horizontal line");
            System.out.print("Your choice: ");
            choice = input.nextInt();
            if (String.valueOf(choice).matches("[^1234]")) {
                System.out.println("Invalid choice!");
            }
        } while (choice < 1 || choice > 4);

        transposeMatrix(input, choice);
    }

    private static Matrix getMatrix(Scanner input, String number) {
        System.out.printf("Enter size of %s matrix: ", number);
        Matrix matrix = new Matrix(input.nextInt(), input.nextInt());
        System.out.printf("Enter %s matrix:\n", number);
        matrix.readMatrix(input);
        return matrix;
    }

    private static void addMatrices(Scanner input) {
        Matrix matrixA = getMatrix(input, "first");
        Matrix matrixB = getMatrix(input, "second");

        if (matrixA.getRows() == matrixB.getRows() && matrixA.getCols() == matrixB.getCols()) {
            System.out.println("The addition result is:");
            matrixA.add(matrixB).print();
        } else {
            System.out.println("ERROR");
        }
    }

    private static void multiplyMatrixByConstant(Scanner input) {
        Matrix matrixA = getMatrix(input, "the");
        System.out.print("Enter the constant: ");
        Matrix result = matrixA.multiply(input.nextDouble());
        System.out.println("The multiplication result is:");
        result.print();
    }

    private static void multiplyMatrixByMatrix(Scanner input) {
        Matrix matrixA = getMatrix(input, "first");
        Matrix matrixB = getMatrix(input, "second");
        if (matrixA.getCols() == matrixB.getRows()) {
            System.out.println("The multiplication result is:");
            matrixA.multiply(matrixB).print();
        } else {
            System.out.println("ERROR: First matrix rows must equal second matrix columns!");
        }
    }

    private static void transposeMatrix(Scanner input, int mode) {
        Matrix matrix = getMatrix(input, "the");
        System.out.println("The result is:");
        matrix.transpose(mode).print();
    }

    private static void findMatrixDeterminant(Scanner input) {
        Matrix matrix = getMatrix(input, "the");
        if (matrix.getRows() == matrix.getCols()) {
            System.out.println("The result is:");
            System.out.println(matrix.getDeterminant());
        } else {
            System.out.println("Can't get determinant of non-square matrix");
        }
    }

    private static void findMatrixInverse(Scanner input) {
        Matrix matrix = getMatrix(input, "the");
        double determinant = matrix.getDeterminant();
        if (determinant == 0.0) {
            System.out.println("Determinant of matrix is 0. Can't find inverse!");
            return;
        }
        System.out.println("The result is:");
        matrix.findMatrixInverse(determinant).print();
    }
}

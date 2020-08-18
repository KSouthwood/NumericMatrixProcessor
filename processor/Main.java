package processor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

//        addMatrices(input);
        multiplyMatrices(input);
    }

    private static void addMatrices(Scanner input) {
        Matrix matrixA = new Matrix(input.nextInt(), input.nextInt());
        matrixA.readMatrix(input);

        Matrix matrixB = new Matrix(input.nextInt(), input.nextInt());
        matrixB.readMatrix(input);

        if (matrixA.getRows() == matrixB.getRows() && matrixA.getCols() == matrixB.getCols()) {
            Matrix result = matrixA.add(matrixB);
            result.print();
        } else {
            System.out.println("ERROR");
        }
    }

    private static void multiplyMatrices(Scanner input) {
        Matrix matrixA = new Matrix(input.nextInt(), input.nextInt());
        matrixA.readMatrix(input);
        Matrix result = matrixA.multiply(input.nextInt());
        result.print();
    }
}

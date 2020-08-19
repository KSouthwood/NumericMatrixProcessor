package processor;

import java.util.Scanner;

public class Matrix {
    private int rows;
    private int cols;
    private double[][] matrix;

    Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new double[rows][cols];
    }

    public void readMatrix(Scanner input) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = input.nextDouble();
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Matrix add(Matrix addend) {
        Matrix sum = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                sum.matrix[row][col] = this.matrix[row][col] + addend.matrix[row][col];
            }
        }
        return sum;
    }

    public void print() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(this.matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    public Matrix multiply(int factor) {
        Matrix product = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                product.matrix[row][col] = this.matrix[row][col] * factor;
            }
        }
        return product;
    }

    public Matrix multiply(Matrix factor) {
        Matrix product = new Matrix(this.rows, factor.getCols());
        for (int rowA = 0; rowA < this.rows; rowA++) {
            for (int colB = 0; colB < factor.getCols(); colB++) {
                for (int colA = 0; colA < this.cols; colA++) {
                    product.matrix[rowA][colB] += this.matrix[rowA][colA] * factor.matrix[colA][colB];
                }
            }
        }
        return product;
    }
}

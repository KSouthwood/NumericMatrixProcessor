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
                System.out.printf("%6.2f ", this.matrix[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public Matrix multiply(double factor) {
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

    public Matrix transpose(int mode) {
        Matrix result = new Matrix(this.cols, this.rows);
        switch (mode) {
            case 1: // main diagonal transposition
                for (int row = 0; row < this.rows; row++) {
                    for (int col = 0; col < this.cols; col++) {
                        result.matrix[col][row] = this.matrix[row][col];
                    }
                }
                break;
            case 2: // side diagonal transposition
                for (int row = 0; row < this.rows; row++) {
                    for (int col = 0; col < this.cols; col++) {
                        result.matrix[this.cols - col - 1][this.rows - row - 1] = this.matrix[row][col];
                    }
                }
                break;
            case 3: // vertical transposition
                for (int row = 0; row < this.rows; row++) {
                    for (int col = 0; col < this.cols; col++) {
                        result.matrix[row][this.cols - col - 1] = this.matrix[row][col];
                    }
                }
                break;
            case 4: // horizontal transposition
                for (int row = 0; row < this.rows; row++) {
                    if (this.cols >= 0)
                        System.arraycopy(this.matrix[row], 0, result.matrix[this.rows - row - 1], 0, this.cols);
                }
                break;
        }
        return result;
    }

    public double getDeterminant() {
        return calculateDeterminant(this.matrix);
    }

    private double calculateDeterminant(double[][] matrixA) {
        if (matrixA.length == 2) {
            return (matrixA[0][0] * matrixA[1][1]) - (matrixA[0][1] * matrixA[1][0]);
        }

        double sum = 0.0;

        for (int col = 0; col < matrixA.length; col++) {
            sum += matrixA[0][col] * Math.pow(-1.0, 1 + col + 1) * calculateDeterminant(getSubMatrix(matrixA, 0, col));
        }

        return sum;
    }

    private double[][] getSubMatrix(double[][] matrix, int skipRow, int skipCol) {
        double[][] subMatrix = new double[matrix.length - 1][matrix.length - 1];

        for (int row = 0, subRow = 0; row < matrix.length; row++) {
            if (row == skipRow) {
                continue;
            }
            for (int col = 0, subCol = 0; col < matrix[row].length; col++) {
                if (col == skipCol) {
                    continue;
                }
                subMatrix[subRow][subCol] = matrix[row][col];
                subCol++;
            }
            subRow++;
        }

        return subMatrix;
    }

    public Matrix findMatrixInverse(double determinant) {
        return getCofactors().multiply(1.0 / determinant);
    }

    private Matrix getCofactors() {
        Matrix cofactorMatrix = new Matrix(this.rows, this.cols);
        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                cofactorMatrix.matrix[row][col] = Math.pow(-1.0, (row + 1) + (col + 1)) * calculateDeterminant(getSubMatrix(this.matrix, row, col));
            }
        }
        return cofactorMatrix.transpose(1);
    }
}

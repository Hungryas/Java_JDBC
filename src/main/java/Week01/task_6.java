package Week01;

import java.util.Arrays;

public class task_6 {

    // умножение матриц
    public static int[][] matrixMultiply(int[][] matrix1, int[][] matrix2) {
        int[][] result_matrix = new int[matrix1.length][matrix2[0].length]; // создаем матрицу для результата сложения
        for (int i = 0; i < matrix1.length; i++) {          // количество строк из matrix1
            for (int j = 0; j < matrix2[0].length; j++) {   // количество столбцов из matrix2
                for (int k = 0; k < matrix2.length; k++) {  // k = столбцы matrix1 = строки matrix2
                    result_matrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result_matrix;
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        int[][] matrix2 = {{3, 2, 1}, {3, 2, 1}, {3, 2, 1}};
        System.out.println(Arrays.deepToString(matrixMultiply(matrix1, matrix2)));
    }
}
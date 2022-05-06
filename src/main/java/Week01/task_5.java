package Week01;

import java.util.Arrays;

public class task_5 {

    // сложение матриц
    public static int[][] matrixAdd(int[][] matrix1, int[][] matrix2) {
        int[][] result_matrix = Arrays.copyOf(matrix1, matrix1.length); // создаем матрицу для результата сложения
        for (int i = 0; i < matrix1.length; i++) {          // количество строк должно быть одинаковым
            for (int j = 0; j < matrix1[i].length; j++) {   // количество столбцов должно быть одинаковым
                result_matrix[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result_matrix;
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        int[][] matrix2 = {{3, 2, 1}, {3, 2, 1}, {3, 2, 1}};
        System.out.println(Arrays.deepToString(matrixAdd(matrix1, matrix2)));
    }
}
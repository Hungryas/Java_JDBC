package Week_01;

import java.util.Arrays;

public class task_3 {

    // возврат массива из n элементов с рандомными значениями
    public static double[] createRandomArray(int n) {
        double[] array = new double[n];
        for (int i = 0; i < n; i++) array[i] = Math.random();
        return array;
    }

    public static void main(String[] args) {
        int n = (int) (10 * Math.abs(Math.random()));
        System.out.println(n);
        double[] arr = createRandomArray(n);
        System.out.println(Arrays.toString(arr));
    }
}

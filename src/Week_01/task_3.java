package Week_01;

import java.util.Arrays;

public class task_3 {

    // возврат массива из n элементов с рандомными значениями
    public static double[] createRandomArray(int n) {
        double[] array = new double[n];
        for (int i = 0; i < n; i++) array[i] = 10 * Math.random(); // заполняем массив
        return array;
    }

    // вывод среднего, максимального и минимального значения массива
    public static void getMixMaxAvg(double[] arr) {
        double min = arr[0], max = arr[0], sum = 0; // задаем начальные значения
        for (double x : arr
        ) {
            min = Math.min(min, x);
            max = Math.max(max, x);
            sum += x;
        }
        System.out.println("Минимальное значение: " + min);
        System.out.println("Максимальное значение: " + max);
        System.out.println("Среднее значение: " + (sum / arr.length));
    }


    public static void main(String[] args) {
        int n = (int) (10 * Math.abs(Math.random() + 1)); // определяем размерность массива от 1 до 11
        double[] arr = createRandomArray(n);
        System.out.println(Arrays.toString(arr));
        getMixMaxAvg(arr);
    }
}

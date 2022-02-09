package Week_01;

import java.util.Arrays;

public class task_4 {
    // возврат копии массива test_array, в котором нет вхождений элемента elem
    public static int[] removeElement(int[] test_array, int elem) {
        int count = 0;
        for (int i = 0; i < test_array.length; i++) {
            if (test_array[i] != elem)
                test_array[i - count] = test_array[i];
            else {
                count++; // количество вхождений elem
            }
        }
        return Arrays.copyOf(test_array, test_array.length - count);
    }

    public static void main(String[] args) {
        int[] test_array = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(Arrays.toString(removeElement(test_array, 3)));
    }
}
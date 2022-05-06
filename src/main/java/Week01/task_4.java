package Week01;

import java.util.Arrays;

public class task_4 {

    // возврат копии массива testArray, в котором нет вхождений элемента elem
    public static int[] removeElement(int[] testArray, int elem) {
        int count = 0;
        for (int i = 0; i < testArray.length; i++) {
            if (testArray[i] != elem)
                testArray[i - count] = testArray[i];
            else count++; // количество вхождений elem
        }
        return Arrays.copyOf(testArray, testArray.length - count);
    }

    public static void main(String[] args) {
        int[] testArray = {1, 2, 3, 4, 5, 6, 7, 8};
        System.out.println(Arrays.toString(removeElement(testArray, 3)));
    }
}
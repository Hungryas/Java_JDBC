package Week_02;

public class task_4 {

    // удаление дубликатов слов в строке
    public static String removeDuplicateWords(String sentence) {
        String[] words_array = sentence.split(" ");
        StringBuilder result_string = new StringBuilder(words_array[0]);
        for (int i = 1; i < words_array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (words_array[i].equals(words_array[j])) {
                    words_array[i] = "";
                    break;
                }
            }
            if (!words_array[i].isEmpty()) result_string.append(" ").append(words_array[i]);
        }
        return result_string.toString();
    }

    public static void main(String[] args) {
        String example = "один один три два два два";
        System.out.println(removeDuplicateWords(example)); // результат: "один три два"
    }
}

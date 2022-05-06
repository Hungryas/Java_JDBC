package Week02;

public class task_4 {

    // удаление дубликатов слов в строке
    public static String removeDuplicateWords(String sentence) {
        String[] wordsArray = sentence.split(" ");
        StringBuilder resultString = new StringBuilder(wordsArray[0]);
        for (int i = 1; i < wordsArray.length; i++) {
            for (int j = 0; j < i; j++) {
                if (wordsArray[i].equals(wordsArray[j])) {
                    wordsArray[i] = "";
                    break;
                }
            }
            if (!wordsArray[i].isEmpty()) resultString.append(" ").append(wordsArray[i]);
        }
        return resultString.toString();
    }

    public static void main(String[] args) {
        String example = "один один три два два два";
        System.out.println(removeDuplicateWords(example)); // результат: "один три два"
    }
}

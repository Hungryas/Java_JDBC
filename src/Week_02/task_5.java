package Week_02;

public class task_5 {

    // возврат true, если каждая буква встречается в слове одинаковое число раз
    public static Boolean hasSameOccurrences(String sentence) {
        int[] matches = new int[sentence.length()];
        for (int i = 0; i <= sentence.length(); i++) {
            String substring = sentence.replace(sentence.substring(0, 1), "");
            matches[i] = sentence.length() - substring.length(); // вычисляем количество вхождений
            sentence = substring;
            if ((i > 0) && (matches[i] != matches[i - 1])) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String sameOccurrencesCount = "abcabc";
        System.out.println(hasSameOccurrences(sameOccurrencesCount)); // результат: true
        String diffOccurrencesCount = "abca";
        System.out.println(hasSameOccurrences(diffOccurrencesCount)); // результат: false
    }
}

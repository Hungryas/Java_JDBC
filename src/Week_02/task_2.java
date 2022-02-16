package Week_02;

public class task_2 {

    //  перевод 1й буквы каждого слова в строке в заглавную
    public static String capitalizeEachWord(String sentence) {
        String[] words_array = sentence.split(" ");
        StringBuilder result_string = new StringBuilder();
        for (String s : words_array) {
            String replaced_letter = s.substring(0, 1);
            result_string.append(" ").append(replaced_letter.toUpperCase()).append(s.substring(1));
        }
        return result_string.toString().trim();
    }

    public static void main(String[] args) {
        String example = "Каждый охотник желает знать где сидит фазан";
        System.out.println(capitalizeEachWord(example)); // результат: "Каждый Охотник Желает Знать Где Сидит Фазан"
    }
}

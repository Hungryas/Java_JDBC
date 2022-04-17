package Week_02;

public class task_2 {

    //  перевод 1й буквы каждого слова в строке в заглавную
    public static String capitalizeEachWord(String sentence) {
        String[] wordsArray = sentence.split(" ");
        StringBuilder resultString = new StringBuilder();
        for (String s : wordsArray) {
            String replacedLetter = s.substring(0, 1);
            resultString.append(" ").append(replacedLetter.toUpperCase()).append(s.substring(1));
        }
        return resultString.toString().trim();
    }

    public static void main(String[] args) {
        String example = "Каждый охотник желает знать где сидит фазан";
        System.out.println(capitalizeEachWord(example)); // результат: "Каждый Охотник Желает Знать Где Сидит Фазан"
    }
}

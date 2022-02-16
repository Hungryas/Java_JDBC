package Week_02;

public class task_3 {

    // возврат центральной буквы, если число букв нечетно, и двух центральных, если четна
    // на входе строка без пробелов и знаков препинания
    public static String getMiddleCharacter(String str) {
        int middle_index = str.length() / 2 + 1;
        return switch (str.length() % 2) {
            case 0 -> str.substring(middle_index - 2, middle_index);
            case 1 -> str.substring(middle_index - 1, middle_index);
            default -> "";
        };
    }

    public static void main(String[] args) {
        String oddString = "слово";
        System.out.println(getMiddleCharacter(oddString)); // результат: "о"
        String evenString = "пять";
        System.out.println(getMiddleCharacter(evenString)); // результат: "ят"
    }
}

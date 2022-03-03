package Week02;

public class task_3 {

    // возврат центральной буквы, если число букв нечетно, и двух центральных, если четна
    public static String getMiddleCharacter(String string) {
        int middleIndex = string.length() / 2 + 1;
        return switch (string.length() % 2) {
            case 0 -> string.substring(middleIndex - 2, middleIndex);
            case 1 -> string.substring(middleIndex - 1, middleIndex);
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

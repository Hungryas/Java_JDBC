package Week_01;

public class task_2 {

    // вывод в консоль латинского алфафита задом наперед
    public static void printReverseAlphabet() {
        char[] alphabet = new char[26];
        for (int i = alphabet.length - 1; i >= 0; i--) {
            alphabet[i] = (char) (i + 65); // генерируем алфавит по коду ASCII
            System.out.print(alphabet[i]);
        }
    }

    public static void main(String[] args) {
        printReverseAlphabet();
    }
}

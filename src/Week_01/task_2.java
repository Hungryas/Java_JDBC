package Week_01;

public class task_1 {


    public static void printAlphabet() {
        char[] alphabet = new char[26];
        for (int i = 0; i < alphabet.length; i++) {
            alphabet[i] = (char) (i + 65);
            System.out.print(alphabet[i]);
        }
    }

    public static void main(String[] args) {
        printAlphabet();
    }
}

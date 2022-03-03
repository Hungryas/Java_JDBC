package Week02;

public class task_1 {

    // преобразование строки "num1 + num2" и возврат суммы/разности/произведения/частного для элементов
    public static Double calc(String equation) {
        double num1 = Double.parseDouble(equation.substring(0, equation.indexOf(" ")));
        double num2 = Double.parseDouble(equation.substring(equation.lastIndexOf(" ")));
        String operator = equation.substring(equation.indexOf(" ") + 1, equation.lastIndexOf(" "));
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> 0.0;
        };
    }

    public static void main(String[] args) {
        String equation = "123123 + 123123";
        System.out.println(calc(equation)); // результат: 246246
    }
}

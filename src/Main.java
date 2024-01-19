import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in); // Создаем объект Scanner для ввода данных
        System.out.println("Введите пример (+, -, *, /): ");
        while (true) {
            System.out.println("= "+ calc(scanner.nextLine()));
        }
    }

    public static String calc(String input) throws Exception {
        int a, b, result;
        String op;

        input = input.replaceAll("(\\d+)"," $1 ").trim().replaceAll("  ", " ");
        String[] stack = input.split(" ");

        validate(stack);

        a = Integer.parseInt(stack[0]);
        b = Integer.parseInt(stack[2]);
        op = stack[1];

        result = switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new Exception();
        };

        return String.valueOf(result);
    }

    private static void validate(String[] stack) throws Exception {

        String message = "incorrect expression";

        try {
            Integer.parseInt(stack[0]);
            Integer.parseInt(stack[2]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new Exception(message);
        }

        if (stack[1].equals("/") && Integer.parseInt(stack[2]) == 0) {
            throw new Exception("division by zero");
        }

        if (stack.length > 3 ||
            (Integer.parseInt(stack[0]) < 1 || Integer.parseInt(stack[0]) > 10) ||
            (Integer.parseInt(stack[2]) < 1 || Integer.parseInt(stack[2]) > 10)) {
            throw new Exception(message);
        }
    }
}
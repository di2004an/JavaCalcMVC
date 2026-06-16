import java.util.Scanner;

public class TerminalUI {
    private Scanner inputScanner = new Scanner(System.in);

    public void displayTitle() {
        System.out.println(">>> КОНСОЛЬНЫЙ КАЛЬКУЛЯТОР v1.0 <<<");
    }

    public void showMenu() {
        System.out.println("\nВыберите действие:");
        System.out.println(" [1] Решить математическое выражение");
        System.out.println(" [2] Показать все прошлые вычисления");
        System.out.println(" [3] Сохранить историю в текстовый файл");
        System.out.println(" [4] Выйти из приложения");
        System.out.print("Ваш ввод: ");
    }

    public String getUserInput() {
        return inputScanner.nextLine();
    }

    public void showText(String text) {
        System.out.println(text);
    }
}
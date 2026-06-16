import java.util.List;

public class AppLogicController {
    private CalculatorLogic logic;
    private TerminalUI ui;

    public AppLogicController(CalculatorLogic logic, TerminalUI ui) {
        this.logic = logic;
        this.ui = ui;
    }

    public void startApplication() {
        logic.initLogic();
        ui.displayTitle();
        boolean isRunning = true;

        while (isRunning) {
            ui.showMenu();
            String choice = ui.getUserInput();

            switch (choice) {
                case "1":
                    ui.showText("Введите уравнение:");
                    String eq = ui.getUserInput();
                    ui.showText("РЕЗУЛЬТАТ: " + logic.calculate(eq));
                    break;
                case "2":
                    ui.showText("--- ЗАПИСИ ОПЕРАЦИЙ ---");
                    List<String> hist = logic.getHistoryLog();
                    if (hist.isEmpty()) {
                        ui.showText("Список пуст.");
                    } else {
                        for(int i = 0; i < hist.size(); i++) {
                            ui.showText("[" + i + "] " + hist.get(i));
                        }
                    }
                    break;
                case "3":
                    ui.showText("Укажите имя файла для выгрузки (или Enter для сохранения по умолчанию):");
                    String filePath = ui.getUserInput();
                    if (logic.exportHistory(filePath)) {
                        ui.showText("Файл успешно экспортирован.");
                    } else {
                        ui.showText("Произошла ошибка записи.");
                    }
                    break;
                case "4":
                    ui.showText("Завершение работы программы.");
                    isRunning = false;
                    break;
                default:
                    ui.showText("Не распознано. Введите цифру от 1 до 4.");
            }
        }
    }
}
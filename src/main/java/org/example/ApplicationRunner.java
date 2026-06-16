public class ApplicationRunner {
    public static void main(String[] args) {
        CalculatorLogic logic = new CalculatorLogic();
        TerminalUI ui = new TerminalUI();

        AppLogicController controller = new AppLogicController(logic, ui);
        controller.startApplication();
    }
}
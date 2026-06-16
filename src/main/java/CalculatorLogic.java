import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CalculatorLogic {
    private final String LOG_FILE = "session_history.txt";
    private List<String> historyLog = new ArrayList<>();

    public void initLogic() {
        try (BufferedReader br = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                historyLog.add(line);
            }
        } catch (IOException ignored) { }
    }

    public String calculate(String expression) {
        try {
            String formatted = expression.replaceAll("\\s+", "").replace(",", ".");
            Expression exp = new ExpressionBuilder(formatted).build();
            double res = exp.evaluate();

            String finalAnswer = (res == Math.floor(res)) ? String.valueOf((long)res) : String.valueOf(res);
            String fullOperation = expression + " = " + finalAnswer;

            historyLog.add(fullOperation);
            saveSingleRecord(fullOperation);
            return finalAnswer;
        } catch (Exception e) {
            return "Ошибка! Проверьте правильность расстановки скобок и знаков.";
        }
    }

    private void saveSingleRecord(String record) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            bw.write(record + "\n");
        } catch (IOException ignored) { }
    }

    public List<String> getHistoryLog() {
        return historyLog;
    }

    public boolean exportHistory(String path) {
        String targetPath = (path == null || path.trim().isEmpty()) ? "exported_logs.txt" : path;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath))) {
            for (String s : historyLog) {
                bw.write(s + "\n");
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
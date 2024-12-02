package oncall.view.input.parser;

import java.util.ArrayList;
import java.util.List;
import oncall.model.domain.schedule.OnCallMonth;
import oncall.model.domain.worker.Worker;

public class InputParser {
    private final static String DELIMITER = ",";

    public static OnCallMonth parseMonthAndStartDay(String input) {
        List<String> monthAndStartDay = List.of(input.split(DELIMITER));
        String month = monthAndStartDay.getFirst();
        String startDay = monthAndStartDay.getLast();
        OnCallMonth onCallMonth = new OnCallMonth(month, startDay);
        return onCallMonth;
    }

    public static List<Worker> parseWorkers(String input) {
        List<String> nicknames = List.of(input.split(DELIMITER));
        List <Worker> workers = new ArrayList<>();
        for (String nickname : nicknames) {
            Worker worker = new Worker(nickname);
            workers.add(worker);
        }
        return workers;
    }
}

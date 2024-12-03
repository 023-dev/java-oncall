package oncall.view.output;

import oncall.model.service.Scheduler;

public class OutputView {
    public static void printSchedule(String schedule) {
        System.out.println(schedule);
    }

    public static void printError(String message) {
        System.out.println(message);
    }
}

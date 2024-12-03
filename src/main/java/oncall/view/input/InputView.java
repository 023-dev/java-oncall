package oncall.view.input;

import static oncall.view.input.parser.InputParser.*;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import oncall.exception.custom.InvalidException;
import oncall.model.domain.schedule.Schedule;
import oncall.model.domain.worker.HolidayWorkers;
import oncall.model.domain.worker.WeekdayWorkers;
import oncall.model.domain.worker.Worker;
import oncall.model.dto.worker.WorkersDto;

public class InputView {
    private static final String PROMPT_MESSAGE_OF_MONTH_AND_START_DAY = "비상 근무를 배정할 월과 시작 요일을 입력하세요> ";
    private static final String PROMPT_MESSAGE_OF_WEEKDAY_WORKER = "평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";
    private static final String PROMPT_MESSAGE_OF_HOLIDAY_WORKER = "휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ";

    public Schedule readMonthAndStartDay() {
        prompt(PROMPT_MESSAGE_OF_MONTH_AND_START_DAY);
        String input = read();
        return parseMonthAndStartDay(input);
    }

    public WorkersDto readWorkers() {
        WeekdayWorkers weekdayWorkers = readWeekdayWorker();
        HolidayWorkers holidayWorkers = readHolidayWorker(weekdayWorkers);
        return new WorkersDto(weekdayWorkers, holidayWorkers);
    }

    private static WeekdayWorkers readWeekdayWorker() {
        prompt(PROMPT_MESSAGE_OF_WEEKDAY_WORKER);
        String input = read();
        List<Worker> workers = parseWorkers(input);
        return new WeekdayWorkers(workers);
    }

    private static HolidayWorkers readHolidayWorker(WeekdayWorkers weekdayWorkers) {
        prompt(PROMPT_MESSAGE_OF_HOLIDAY_WORKER);
        String input = read();
        List<Worker> workers = parseWorkers(input);
        return new HolidayWorkers(workers, weekdayWorkers);
    }

    private static void prompt(String message) {
        System.out.print(message);
    }

    private static String read() {
        String input = Console.readLine();
        validate(input);
        return input;
    }

    private static void validate(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidException("입력 값이 비어있습니다. 다시 입력해주세요.");
        }
    }
}

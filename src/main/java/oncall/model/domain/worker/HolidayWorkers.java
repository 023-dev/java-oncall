package oncall.model.domain.worker;

import java.util.List;
import oncall.exception.custom.InvalidException;

public class HolidayWorkers  extends Workers{

    public HolidayWorkers(List<Worker> holidayWorkers, WeekdayWorkers weekdayWorkers) {
        super(holidayWorkers);
        validate(holidayWorkers, weekdayWorkers);
    }

    private void validate(List<Worker> holidayWorkers, WeekdayWorkers weekdayWorkers) {
        List<Worker> weekdayWorkerList = weekdayWorkers.getWorkers();

        for (Worker holidayWorker : holidayWorkers) {
            if (!weekdayWorkerList.contains(holidayWorker)) {
                throw new InvalidException("휴일 근무자 "+holidayWorker.getNickname()+"는 평일 근무자에 포함되어야 합니다.");
            }
        }
    }
}

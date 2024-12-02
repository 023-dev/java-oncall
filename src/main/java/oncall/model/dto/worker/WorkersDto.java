package oncall.model.dto.worker;

import oncall.model.domain.worker.HolidayWorkers;
import oncall.model.domain.worker.WeekdayWorkers;

public class WorkersDto {
    WeekdayWorkers weekdayWorkers;
    HolidayWorkers holidayWorkers;

    public WorkersDto(WeekdayWorkers weekdayWorkers, HolidayWorkers holidayWorkers) {
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    public WeekdayWorkers getWeekdayWorkers() { return weekdayWorkers; }

    public HolidayWorkers getHolidayWorkers() {
        return holidayWorkers;
    }
}

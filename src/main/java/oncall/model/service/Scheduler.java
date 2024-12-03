package oncall.model.service;

import oncall.exception.custom.InvalidException;
import oncall.model.domain.schedule.Schedule;
import oncall.model.domain.worker.HolidayWorkers;
import oncall.model.domain.worker.WeekdayWorkers;
import oncall.model.domain.worker.Worker;
import oncall.model.domain.worker.Workers;

public class Scheduler {
    private final Schedule schedule;
    private final Workers weekdayWorkers;
    private final Workers holidayWorkers;

    public Scheduler(Schedule schedule, WeekdayWorkers weekdayWorkers, HolidayWorkers holidayWorkers) {
        this.schedule = schedule;
        this.weekdayWorkers = weekdayWorkers;
        this.holidayWorkers = holidayWorkers;
    }

    public String generate() {
        StringBuilder result = new StringBuilder();
        int daysInMonth = schedule.getDaysOfMonth();
        int weekdayIndex = 0;
        int holidayIndex = 0;
        String lastAssignedEmployee = "";

        for (int day = 1; day <= daysInMonth; day++) {
            String currentDay = schedule.getDayOfWeek(day); // 현재 요일
            boolean isHoliday = schedule.isHoliday(day, currentDay); // 휴일 여부
            String holidayName = schedule.getHoliday(day); // 공휴일 이름(있다면)

            String assignedEmployee;

            // 평일/휴일에 따라 근무자 배정
            if (isHoliday) {
                assignedEmployee = assignWorker(holidayWorkers, holidayIndex, lastAssignedEmployee);
                holidayIndex = (holidayIndex + 1) % holidayWorkers.size();
            } else {
                assignedEmployee = assignWorker(weekdayWorkers, weekdayIndex, lastAssignedEmployee);
                weekdayIndex = (weekdayIndex + 1) % weekdayWorkers.size();
            }

            // 결과 문자열에 추가
            appendResult(result, schedule.getMonth(), day, currentDay, assignedEmployee, isHoliday, holidayName);

            lastAssignedEmployee = assignedEmployee; // 마지막 근무자 업데이트
        }

        return result.toString();
    }

    private String assignWorker(Workers workers, int index, String lastAssignedEmployee) {
        Worker worker = workers.getWorker(index);

        // 연속 근무 방지
        if (worker.getNickname().equals(lastAssignedEmployee)) {
            return swapAndGetNext(workers, index);
        }

        return worker.getNickname();
    }

    private String swapAndGetNext(Workers workers, int index) {
        int nextIndex = (index + 1) % workers.size();
        if (index == nextIndex) {
            throw new InvalidException("교환이 불가능한 상태입니다. 순번 리스트를 확인해주세요.");
        }

        Worker temp = workers.getWorker(index);
        workers.set(index, workers.getWorker(nextIndex));
        workers.set(nextIndex, temp);

        return workers.getWorker(index).getNickname();
    }

    private void appendResult(StringBuilder result, int month, int day, String currentDay,
                              String assignedEmployee, boolean isHoliday, String holidayName) {
        if (isHoliday) {
            if (holidayName != null) {
                result.append(String.format("%d월 %d일 %s(휴일, %s) %s\n",
                        month, day, currentDay, holidayName, assignedEmployee));
            } else {
                result.append(String.format("%d월 %d일 %s(휴일) %s\n",
                        month, day, currentDay, assignedEmployee));
            }
        } else {
            result.append(String.format("%d월 %d일 %s %s\n", month, day, currentDay, assignedEmployee));
        }
    }
}

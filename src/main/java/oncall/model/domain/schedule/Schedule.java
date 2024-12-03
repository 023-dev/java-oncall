package oncall.model.domain.schedule;

import java.util.List;
import oncall.exception.custom.InvalidException;

public class OnCallMonth {
    private static final int MIN_MONTH = 1;
    private static final int MAX_MONTH = 12;
    private static final List<String> DAYS_OF_WEEK = List.of("일", "월", "화", "수", "목", "금", "토");

    private final int month;
    private final String startDay;

    public OnCallMonth(String month, String startDay) {
        this.month = validateMonth(month);
        this.startDay = validateStartDay(startDay);
    }

    private int validateMonth(String month) {
        try {
            int monthInt = Integer.parseInt(month);
            if (monthInt < MIN_MONTH || monthInt > MAX_MONTH) {
                throw new InvalidException("1월부터 12월 사이의 숫자를 입력해주세요.");
            }
            return monthInt;
        } catch (NumberFormatException e) {
            throw new InvalidException("숫자를 입력해주세요.");
        }
    }

    private String validateStartDay(String startDay) {
        if (!DAYS_OF_WEEK.contains(startDay)) {
            throw new InvalidException("요일을 입력해주세요.");
        }
        return startDay;
    }

    public int getDatsOfMonth() {
        return switch (month) {
            case 2 -> 28;
            case 4, 6, 9, 11 -> 30;
            default -> 31;
        };
    }

    public String getDayOfWeek(int day) {
        if (day < 1 || day > getDatsOfMonth()) {
            throw new InvalidException("잘못된 날짜입니다. 1일에서 " + getDatsOfMonth() + "일 사이여야 합니다.");
        }
        int startDayIndex = DAYS_OF_WEEK.indexOf(startDay);
        return DAYS_OF_WEEK.get((startDayIndex+1)%7);
    }

    public int getMonth() {
        return month;
    }

    public String getStartDay() {
        return startDay;
    }
}
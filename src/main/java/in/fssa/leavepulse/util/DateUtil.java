package in.fssa.leavepulse.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtil {
	
	public static long getDaysWithoutSundays(LocalDate startDate, LocalDate endDate) {
		
        long days = ChronoUnit.DAYS.between(startDate, endDate);

        long sundayCount = 0;
        LocalDate current = startDate;
        
        for (int i = 0; i <= days; i++) {
            if (current.getDayOfWeek() == DayOfWeek.SUNDAY) {
                sundayCount++;
            }
            current = current.plusDays(1);
        }

        return days - sundayCount;
    }

}

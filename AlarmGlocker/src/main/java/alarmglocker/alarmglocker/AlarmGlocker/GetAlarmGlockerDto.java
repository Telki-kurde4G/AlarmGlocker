package alarmglocker.alarmglocker.AlarmGlocker;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class GetAlarmGlockerDto {
    private LocalDate date;
    private boolean isOne;
    private String label;
    private boolean recurring;
}

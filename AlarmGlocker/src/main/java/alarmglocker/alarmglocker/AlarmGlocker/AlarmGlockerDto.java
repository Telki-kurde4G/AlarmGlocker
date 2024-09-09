package alarmglocker.alarmglocker.AlarmGlocker;

import alarmglocker.alarmglocker.User.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AlarmGlockerDto {
    private Long id;
    @NotBlank(message = "date must not be null or empty")
    private LocalDate date;

    @NotNull
    private boolean isOne;

    private String label;

    @NotNull(message = "recurring must not be null")
    private boolean recurring;

    private Long userId;

}

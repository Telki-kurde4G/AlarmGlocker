package alarmglocker.alarmglocker.AlarmGlocker;

import alarmglocker.alarmglocker.User.UserEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class AlarmGlockerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "date must not be null or empty")
    @Column(nullable = false, updatable = false)
    LocalDate date;

    @NotNull
    private boolean isOne;

    private String label;

    @NotNull(message = "recurring must not be null")
    private boolean recurring;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}

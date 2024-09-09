package alarmglocker.alarmglocker.User;

import alarmglocker.alarmglocker.AlarmGlocker.AlarmGlockerEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;

@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "userName must not be null or empty")
    private String userName;

    @NotBlank(message = "email must not be null or empty")
    private String email;

    @NotEmpty(message = "Password must not be null or empty")
    private String passwordHash;

    @NotNull(message = "")
    @Column(nullable = false)
    private boolean activeButton = true;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ArrayList<AlarmGlockerEntity> alarmGlockerEntityArrayList;
}

package alarmglocker.alarmglocker.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    @NotNull
    private  Long id;
    @NotBlank(message = "userName must not be null or empty")
    private String userName;

    @NotBlank(message = "email must not be null or empty")
    private String email;

    @NotEmpty(message = "Password must not be null or empty")
    private String passwordHash;
}

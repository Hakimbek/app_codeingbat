package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {
    @NotNull(message = "email must not be null")
    private String email;

    @NotNull(message = "password must not be null")
    private String password;
}

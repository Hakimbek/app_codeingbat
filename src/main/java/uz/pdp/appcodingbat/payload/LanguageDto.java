package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LanguageDto {
    @NotNull(message = "language must not be null")
    private String name;
}

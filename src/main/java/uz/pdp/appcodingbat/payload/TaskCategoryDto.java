package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class TaskCategoryDto {
    @NotNull(message = "name must not be null")
    private String name;

    private String info;

    private List<Integer> languages;
}

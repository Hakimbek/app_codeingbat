package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {
    @NotNull(message = "name must not be null")
    private String name;

    private String text;

    private String solution;

    private String example;

    @NotNull(message = "taskCategory must not be null")
    private Integer taskCategoryId;
}

package uz.pdp.appcodingbat.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {
    @NotNull(message = "userId must not be null")
    private Integer userId;

    @NotNull(message = "taskId must not be null")
    private Integer taskId;

    @NotNull(message = "correct must not be null")
    private Boolean correct;
}

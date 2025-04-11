package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneratedProblemResponse {
    private int problemNumber;
    private ProblemDTO problem;
    private AnswerDTO answer;
}

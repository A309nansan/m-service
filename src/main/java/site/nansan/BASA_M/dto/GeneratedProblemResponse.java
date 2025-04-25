package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneratedProblemResponse {
    private int problemNumber;
    private Problem problem;
    private Answer answer;
}

package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import site.nansan.BASA_M.dto.answer.AnswerDTO;
import site.nansan.BASA_M.dto.problem.ProblemDTO;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalculationResponse {
    private ProblemDTO problem;
    private AnswerDTO answer;
}

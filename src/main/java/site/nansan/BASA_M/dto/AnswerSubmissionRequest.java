package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import site.nansan.BASA_M.dto.answer.AnswerDTO;
import site.nansan.BASA_M.dto.problem.ProblemDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerSubmissionRequest {
    private LocalDate solvedDate;
    private LocalDateTime solvedTime;

    private ProblemDTO generatedProblem;
    private AnswerDTO generatedAnswer;
    private AnswerDTO userAnswer;
}

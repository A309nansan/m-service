package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.nansan.BASA_M.dto.answer.AnswerDTO;
import site.nansan.BASA_M.dto.problem.ProblemDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerSubmissionRequest {
    private LocalDate solvedDate;
    private LocalDateTime solvedTime;

    private ProblemDTO generatedProblem;
    private AnswerDTO generatedAnswer;
    private AnswerDTO userAnswer;
}

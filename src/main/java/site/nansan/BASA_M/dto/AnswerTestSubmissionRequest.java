package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerTestSubmissionRequest {

    private int problemNumber;
    private int group;
    private int child;
    private LocalDate solvedDate;
    private String solvedTime;

    private Problem generatedProblem;
    private Answer generatedAnswer;
    private Answer userAnswer;

}

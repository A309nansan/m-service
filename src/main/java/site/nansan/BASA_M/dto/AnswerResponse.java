package site.nansan.BASA_M.dto;

import lombok.Builder;
import lombok.Getter;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.ProblemErrorCode;
import site.nansan.BASA_M.domain.answer.Answer;

import java.util.Set;

@Getter
@Builder
public class AnswerResponse {
    private final int categoryCode;
    private final int problemNumber;
    private final String solvedTime;

    private final Problem generatedProblem;
    private final Answer generatedAnswer;
    private final Answer userAnswer;

    private final Boolean isCorrect;
    private final int basaTotalScore;
    private final int basaMyScore;
    private final Set<ProblemErrorCode> errorCodes;
}

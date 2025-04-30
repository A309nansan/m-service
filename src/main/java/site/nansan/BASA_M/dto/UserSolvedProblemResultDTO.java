package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.ProblemErrorCode;
import site.nansan.BASA_M.domain.UserSolvedProblem;
import site.nansan.BASA_M.domain.UserSolvedTestProblem;
import site.nansan.BASA_M.domain.answer.Answer;

import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSolvedProblemResultDTO {
    private int problemNumber;
    private String solvedTime;

    private Problem generatedProblem;
    private Answer generatedAnswer;
    private Answer userAnswer;

    private boolean isCorrect;
    private int basaTotalScore;
    private int basaUserScore;
    private Set<ProblemErrorCode> errorCodes;

    public static UserSolvedProblemResultDTO from(UserSolvedProblem entity) {
        return UserSolvedProblemResultDTO.builder()
                .problemNumber(entity.getProblemNumber())
                .isCorrect(entity.getIsCorrect())
                .solvedTime(entity.getSolvedTime())
                .generatedProblem(entity.getGeneratedProblem())
                .generatedAnswer(entity.getGeneratedAnswer())
                .userAnswer(entity.getUserAnswer())
                .basaTotalScore(entity.getBasaTotalScore())
                .basaUserScore(entity.getBasaMyScore())
                .errorCodes(entity.getErrorCodes())
                .build();
    }

    public static UserSolvedProblemResultDTO from(UserSolvedTestProblem entity) {

        return UserSolvedProblemResultDTO.builder()
                .problemNumber(entity.getProblemNumber())
                .isCorrect(entity.getIsCorrect())
                .solvedTime(entity.getSolvedTime())
                .generatedProblem(entity.getGeneratedProblem())
                .generatedAnswer(entity.getGeneratedAnswer())
                .userAnswer(entity.getUserAnswer())
                .basaTotalScore(entity.getBasaTotalScore())
                .basaUserScore(entity.getBasaMyScore())
                .errorCodes(entity.getErrorCodes())
                .build();
    }

}

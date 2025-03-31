package site.nansan.BASA_M.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import site.nansan.BASA_M.domain.ProblemErrorCode;
import site.nansan.BASA_M.domain.UserSolvedProblem;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserSolvedProblemResultDTO {
    private int problemNumber;
    private LocalDateTime solvedTime;

    private ProblemDTO generatedProblem;
    private AnswerDTO generatedAnswer;
    private AnswerDTO userAnswer;
    private Set<ProblemErrorCode> errorCodes;

    public static UserSolvedProblemResultDTO from(UserSolvedProblem entity) {
        return UserSolvedProblemResultDTO.builder()
                .problemNumber(entity.getProblemNumber())
                .solvedTime(entity.getSolvedTime())
                .generatedProblem(entity.getGeneratedProblem())
                .generatedAnswer(entity.getGeneratedAnswer())
                .userAnswer(entity.getUserAnswer())
                .errorCodes(entity.getErrorCodes())
                .build();
    }
}

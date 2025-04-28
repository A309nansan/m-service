package site.nansan.BASA_M.dto;

import lombok.Builder;
import lombok.Getter;
import site.nansan.BASA_M.domain.ProblemErrorCode;

import java.util.Set;

@Getter
@Builder
public class EvaluationResultDTO {

    private final Boolean isCorrect;
    private final int basaTotalScore;
    private final int basaMyScore;
    private final Set<ProblemErrorCode> errorCodes;
}

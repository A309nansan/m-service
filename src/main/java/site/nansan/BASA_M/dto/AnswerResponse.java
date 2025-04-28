package site.nansan.BASA_M.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import site.nansan.BASA_M.domain.ProblemErrorCode;

import java.util.Set;

@Getter
@Builder
@RequiredArgsConstructor
public class AnswerResponse {

    private final Boolean isCorrect;
    private final int basaTotalScore;
    private final int basaMyScore;
    private final Set<ProblemErrorCode> errorCodes;

    public static AnswerResponse from(EvaluationResultDTO dto) {
        return AnswerResponse.builder()
                .isCorrect(dto.getIsCorrect())
                .basaTotalScore(dto.getBasaTotalScore())
                .basaMyScore(dto.getBasaMyScore())
                .errorCodes(dto.getErrorCodes())
                .build();
    }
}


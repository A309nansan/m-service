package site.nansan.BASA_M.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AnswerEvaluationDTO {
    Boolean isCorrect;
    int basaTotalScore;
    int basaMyScore;
}

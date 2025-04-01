package site.nansan.BASA_M.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerEvaluationDTO {
    Boolean isCorrect;
    int score;
}

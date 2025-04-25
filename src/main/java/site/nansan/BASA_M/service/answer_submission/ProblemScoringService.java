package site.nansan.BASA_M.service.answer_submission;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.dto.AnswerEvaluationDTO;
import site.nansan.BASA_M.domain.answer.Calculate;

@Service
public class ProblemScoringService {

    public AnswerEvaluationDTO computeTotalScore(Answer expectedAnswer, Answer submittedAnswer, Operator operator) {
        int totalScore = expectedAnswer.calculateAnswerScore();

        // 정답이 동일하면 전부 정답 처리
        if (isResultsFullyEqual(expectedAnswer.getResult(), submittedAnswer.getResult())) {
            return AnswerEvaluationDTO.builder()
                    .isCorrect(true)
                    .basaTotalScore(totalScore)
                    .basaMyScore(totalScore)
                    .build();
        }

        // 나눗셈 문제는 결과값이 틀리면 무조건 0점 처리
        if (operator == Operator.DIV) {
            return AnswerEvaluationDTO.builder()
                    .isCorrect(false)
                    .basaTotalScore(totalScore)
                    .basaMyScore(0)
                    .build();
        }

        int calculationsScore = scoreCalculations(expectedAnswer, submittedAnswer);
        int remainderScore = scoreRemainder(expectedAnswer, submittedAnswer);
        int resultScore = scoreResult(expectedAnswer.getResult(), submittedAnswer.getResult());

        int userScore = resultScore + calculationsScore + remainderScore;

        return AnswerEvaluationDTO.builder()
                .isCorrect(false)
                .basaTotalScore(totalScore)
                .basaMyScore(userScore)
                .build();
    }

    private int scoreResult(Result expected, Result submitted) {
        int matched = 0;
        int expectedSize = expected.getSize();
        int submittedSize = submitted.getSize();

        for (int i = 0; i < Math.min(expectedSize, submittedSize); i++) {
            if (expected.getDigitAt(i) == submitted.getDigitAt(i)) {
                matched++;
            }
        }

        // 자릿수 초과에 대한 감점
        int penalty = Math.max(0, submittedSize - expectedSize);
        return Math.max(0, matched - penalty);
    }

    private boolean isResultsFullyEqual(Result expected, Result submitted) {
        if (expected.getSize() != submitted.getSize()) return false;
        for (int i = 0; i < expected.getSize(); i++) {
            if (expected.getDigitAt(i) != submitted.getDigitAt(i)) return false;
        }
        return true;
    }

    private int scoreCalculations(Answer expected, Answer submitted) {
        int total = 0;

        total += scoreSection(expected.getCalculate1(), submitted.getCalculate1());
        total += scoreSection(expected.getCalculate2(), submitted.getCalculate2());
        total += scoreSection(expected.getCalculate3(), submitted.getCalculate3());

        return total;
    }

    private int scoreSection(Calculate expected, Calculate submitted) {
        if (expected == null || submitted == null) return 0;

        int score = 0;
        for (int i = 0; i < expected.getSize(); i++) {
            int expectedDigit = expected.getDigitAt(i);
            int submittedDigit = submitted.getDigitAt(i);

            if (expectedDigit == submittedDigit || (expectedDigit == -1 && submittedDigit == 0)) {
                score++;
            }
        }
        return score;
    }

    private int scoreRemainder(Answer expected, Answer submitted) {
        if (expected.getRemainder() == null || submitted.getRemainder() == null) return 0;
        return expected.getRemainder().equals(submitted.getRemainder()) ? 1 : 0;
    }
}

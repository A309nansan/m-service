package site.nansan.BASA_M.service.answer_submission;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.AnswerEvaluationDTO;
import site.nansan.BASA_M.dto.AnswerDTO;
import site.nansan.BASA_M.dto.CalculateDTO;
import site.nansan.BASA_M.dto.ResultDTO;

@Service
public class ProblemScoringService {

    public AnswerEvaluationDTO computeTotalScore(AnswerDTO expectedAnswer, AnswerDTO submittedAnswer, Operator problemOperator) {
        int score = expectedAnswer.calculateAnswerScore();

        int expectedResultScore = expectedAnswer.getResult().getSize();
        int userScore = scoreResultDigits(expectedResultScore, expectedAnswer.getResult(), submittedAnswer.getResult());

        if(isAnswerFullyCorrect(expectedResultScore, userScore)){
            return new AnswerEvaluationDTO(true, expectedResultScore, score);
        } else if(problemOperator == Operator.DIV){
            return new AnswerEvaluationDTO(false, expectedResultScore, 0);
        }

        if (expectedAnswer.getCalculate1() != null && submittedAnswer.getCalculate1() != null)
            userScore += scoreSectionDigits(expectedAnswer.getCalculate1(), submittedAnswer.getCalculate1());
        if (expectedAnswer.getCalculate2() != null && submittedAnswer.getCalculate2() != null)
            userScore += scoreSectionDigits(expectedAnswer.getCalculate2(), submittedAnswer.getCalculate2());
        if (expectedAnswer.getCalculate3() != null && submittedAnswer.getCalculate3() != null)
            userScore += scoreSectionDigits(expectedAnswer.getCalculate3(), submittedAnswer.getCalculate3());

        if(expectedAnswer.getRemainder() != null)
            userScore += calculateRemainderScore(expectedAnswer.getRemainder(), submittedAnswer.getRemainder());

        return new AnswerEvaluationDTO(false, expectedResultScore, userScore);
    }

    private int scoreResultDigits(int resultCount, ResultDTO expectedResult, ResultDTO submittedResult) {
        int userCount = submittedResult.getSize();
        int score = 0;
        for (int pos = 0; pos < resultCount; pos++) {
            int expectedDigit = expectedResult.getDigitAt(pos);
            int submittedDigit = submittedResult.getDigitAt(pos);
            if (expectedDigit == submittedDigit) {
                score++;
            }
        }
        // 만약 user가 예상보다 더 많은 자릿수를 사용했다면 초과한 자리수마다 -1점
        int extra = Math.max(0, userCount - resultCount);
        score -= extra;
        return Math.max(0, score);
    }

    private int scoreSectionDigits(CalculateDTO expectedAnswer, CalculateDTO submittedAnswer) {
        int score = 0;
        for (int pos = 0; pos < expectedAnswer.getSize(); pos++) {
            int genDigit = expectedAnswer.getDigitAt(pos);
            int userDigit = submittedAnswer.getDigitAt(pos);
            if (genDigit == userDigit || genDigit == -1 && userDigit == 0) {
                score++;
            }
        }

        return score;
    }

    private int calculateRemainderScore(int expectedAnswer, int submittedAnswer){
        return expectedAnswer == submittedAnswer ? 1 : 0;
    }

    private boolean isAnswerFullyCorrect(int totalResultScore, int userResultScore) {
        return totalResultScore == userResultScore;
    }

}

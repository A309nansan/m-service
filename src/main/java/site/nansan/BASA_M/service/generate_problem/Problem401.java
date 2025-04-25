package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.util.RandomUtil;

/** 받아올림 없는 세자리 + 세자리 */
@Service
public class Problem401 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int hundreds = RandomUtil.generateRandomIntBetween(1, 6);
        int tens = RandomUtil.generateRandomIntBetween(0, 7);
        int ones = RandomUtil.generateRandomIntBetween(0, 7);
        int operand1 = hundreds * 100 + tens * 10 + ones;

        int bHundreds = RandomUtil.generateRandomIntBetween(1, 9 - hundreds);
        int bTens = RandomUtil.generateRandomIntBetween(0, 9 - tens);
        int bOnes = RandomUtil.generateRandomIntBetween(0, 9 - ones);
        int operand2 = bHundreds * 100 + bTens * 10 + bOnes;

        int sum = operand1 + operand2;

        return GeneratedProblemResponse.builder()
                .problem(Problem.builder()
                        .first(operand1)
                        .second(operand2)
                        .operator(Operator.PLUS)
                        .build())
                .answer(Answer.builder()
                        .result(Result.from(sum))
                        .build())
                .build();
    }
}

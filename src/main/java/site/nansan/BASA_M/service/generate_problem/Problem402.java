package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 받아내림 없는 세자리 - 세자리 */
@Service
public class Problem402 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int aHundreds = RandomUtil.generateRandomIntBetween(1, 9);
        int aTens = RandomUtil.generateRandomIntBetween(0, 9);
        int aOnes = RandomUtil.generateRandomIntBetween(0, 9);
        int operand1 = aHundreds * 100 + aTens * 10 + aOnes;

        int bHundreds = RandomUtil.generateRandomIntBetween(1, aHundreds);
        int bTens = RandomUtil.generateRandomIntBetween(0, aTens);
        int bOnes = RandomUtil.generateRandomIntBetween(0, aOnes);
        int operand2 = bHundreds * 100 + bTens * 10 + bOnes;

        int diff = operand1 - operand2;

        return GeneratedProblemResponse.builder()
                .problem(Problem.builder()
                        .first(operand1)
                        .second(operand2)
                        .operator(Operator.MIN)
                        .build())
                .answer(Answer.builder()
                        .result(Result.from(diff))
                        .build())
                .build();
    }
}

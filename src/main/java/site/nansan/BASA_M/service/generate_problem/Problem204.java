package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 받아내림 없는 두자리 - 두자리 */
@Service
public class Problem204 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int operand1 = RandomUtil.generateRandomIntBetween(23, 99);
        if(operand1 % 10 == 0 || operand1 % 10 == 1 || operand1 % 10 == 2){
            operand1 = RandomUtil.generateRandomIntBetween(23, 99);
        }
        int tensPart = RandomUtil.generateRandomIntBetween(1, operand1 / 10);
        int onesPart = RandomUtil.generateRandomIntBetween(0, operand1 % 10);
        int operand2 = tensPart * 10 + onesPart;
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

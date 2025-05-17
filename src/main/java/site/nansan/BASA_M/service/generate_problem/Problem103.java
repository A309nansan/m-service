package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Carry;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.dto.*;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 일의 자리 수 2개의 합이 10 이상인 덧셈 */
@Service
public class Problem103 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int operand1 = RandomUtil.generateRandomNDigitInt(1);
        if(operand1 == 1) {
            operand1 = RandomUtil.generateRandomIntBetween(2, 9);
        }
        int operand2 = RandomUtil.generateRandomIntBetween(10 - operand1, 9);
        int sum = operand1 + operand2;

        return GeneratedProblemResponse.builder()
                .problem(Problem.builder()
                        .first(operand1)
                        .second(operand2)
                        .operator(Operator.PLUS)
                        .build())
                .answer(Answer.builder()
                        .result(Result.from(sum))
                        .carry1(Carry.calculateAdditionCarry(operand1, operand2))
                        .build())
                .build();
    }
}

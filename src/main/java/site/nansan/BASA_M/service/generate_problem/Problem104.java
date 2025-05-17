package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Carry;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.dto.*;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 10 - 몇의 뺄셈 */
@Service
public class Problem104 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        final int fixedOperand = 10;
        int operand2 = RandomUtil.generateRandomNDigitInt(1);
        int result = fixedOperand - operand2;

        return GeneratedProblemResponse.builder()
                .problem(Problem.builder()
                        .first(fixedOperand)
                        .second(operand2)
                        .operator(Operator.MIN)
                        .build())
                .answer(Answer.builder()
                        .result(Result.from(result))
                        .carry1(Carry.calculateSubtractionBorrow(fixedOperand, operand2))
                        .build())
                .build();
    }
}

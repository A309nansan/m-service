package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Carry;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.dto.*;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 받아올림 있는 두자리 + 두자리 */
@Service
public class Problem303 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {
        int operand1 = RandomUtil.generateRandomIntBetween(10, 87);
        while(operand1 % 10 == 0){
            operand1 = RandomUtil.generateRandomIntBetween(10, 87);
            if(operand1 % 10 == 1 || operand1 % 10 == 2){
                operand1 = RandomUtil.generateRandomIntBetween(10, 87);
            }
        }
        int tensPart = RandomUtil.generateRandomNDigitInt(1);
        int onesPart = RandomUtil.generateRandomIntBetween(10 - (operand1 % 10), 9);
        int operand2 = tensPart * 10 + onesPart;
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

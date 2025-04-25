package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Carry;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.dto.*;
import site.nansan.BASA_M.util.RandomUtil;

/** 받아올림 있는 두자리 + 한자리 */
@Service
public class Problem301 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int operand1 = RandomUtil.generateRandomNDigitInt(2);
        while(operand1 % 10 == 0){
            operand1 = RandomUtil.generateRandomNDigitInt(2);
            if(operand1 % 10 == 1 || operand1 % 10 == 2){
                operand1 = RandomUtil.generateRandomNDigitInt(2);
            }
        }
        int operand2 = RandomUtil.generateRandomIntBetween(10 - (operand1 % 10), 9);
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

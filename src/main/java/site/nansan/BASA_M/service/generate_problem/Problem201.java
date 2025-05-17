package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.global.util.RandomUtil;

@Service
public class Problem201 extends ProblemGenerationService{

    /** 받아올림 없는 두자리 + 한자리 */
    @Override
    public GeneratedProblemResponse makeProblem() {

        int operand1 = RandomUtil.generateRandomNDigitInt(2);
        while(operand1 % 10 == 9) {
            operand1 = RandomUtil.generateRandomNDigitInt(2);
            if(operand1 % 10 == 8 || operand1 % 10 == 7) {
                operand1 = RandomUtil.generateRandomNDigitInt(2);
            }
        }
        int operand2 = RandomUtil.generateRandomNDigitIntLessThan(1, 9 - (operand1 % 10));
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

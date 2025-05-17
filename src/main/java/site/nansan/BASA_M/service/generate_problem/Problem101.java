package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 합이 9 이하인 덧셈 */
@Service
public class Problem101 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        // 1과 8은 경우의 수가 1개 밖에 없으니 가중치를 둠
        int operand1 = RandomUtil.generateRandomIntBetween(1, 8);
        if(operand1 == 1 || operand1 == 8) {
            operand1 = RandomUtil.generateRandomIntBetween(1, 8);
        }
        int operand2 = RandomUtil.generateRandomNDigitIntLessThan(1, 9 - operand1);
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

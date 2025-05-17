package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 구구단을 이용한 나눗셈의 몫 구하기 */
@Service
public class Problem501 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int multiplicand = RandomUtil.generateRandomIntBetween(1, 9);
        int multiplier = RandomUtil.generateRandomIntBetween(1, 9);
        int dividend = multiplicand * multiplier;

        return GeneratedProblemResponse.builder()
                .problem(Problem.builder()
                        .first(dividend)
                        .second(multiplier)
                        .operator(Operator.DIV)
                        .build())
                .answer(Answer.builder()
                        .result(Result.from(dividend / multiplier))
                        .build())
                .build();
    }
}

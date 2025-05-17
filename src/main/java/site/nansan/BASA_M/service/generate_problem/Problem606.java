package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 나머지가 있는 세 자리수 / 한 자리수 */
@Service
public class Problem606 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int dividend = RandomUtil.generateRandomIntBetween(10,99);
        int divisor = RandomUtil.generateRandomIntBetween(2,9);
        dividend = dividend * divisor;
        dividend += RandomUtil.generateRandomIntBetween(1,divisor-1);

        return GeneratedProblemResponse.builder()
                .problem(Problem.builder()
                        .first(dividend)
                        .second(divisor)
                        .operator(Operator.DIV)
                        .build())
                .answer(Answer.calculateDivision(dividend,divisor))
                .build();
    }
}

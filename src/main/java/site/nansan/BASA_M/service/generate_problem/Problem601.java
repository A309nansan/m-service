package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 세자리 * 한자리 */
@Service
public class Problem601 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int multiplicand = RandomUtil.generateRandomNDigitInt(3);
        int multiplier = RandomUtil.generateRandomNDigitInt(1);

        return GeneratedProblemResponse.builder()
                .problem(Problem.builder()
                        .first(multiplicand)
                        .second(multiplier)
                        .operator(Operator.MULT)
                        .build())
                .answer(Answer.calculateOneDigitMultiplication(multiplicand, multiplier, true))
                .build();
    }
}

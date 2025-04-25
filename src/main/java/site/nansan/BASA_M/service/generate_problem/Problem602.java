package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.util.RandomUtil;

/** 두자리 * 두자리 */
@Service
public class Problem602 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int multiplicand = RandomUtil.generateRandomNDigitInt(2);
        int multiplier = RandomUtil.generateRandomNDigitInt(2);

        return GeneratedProblemResponse.builder()
                .problem(Problem.builder()
                        .first(multiplicand)
                        .second(multiplier)
                        .operator(Operator.MULT)
                        .build())
                .answer(Answer.calculateTwoDigitMultiplication(multiplicand, multiplier))
                .build();
    }
}

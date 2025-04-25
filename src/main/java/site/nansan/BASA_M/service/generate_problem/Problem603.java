package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.util.RandomUtil;

/** 나머지가 없는 두자리수 나누기 한자리수 */
@Service
public class Problem603 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int dividend = RandomUtil.generateRandomIntBetween(10,99);
        int divisor = RandomUtil.generateRandomIntBetween(1,9);
        while(dividend % divisor != 0){
            dividend = RandomUtil.generateRandomIntBetween(10,99);
            divisor = RandomUtil.generateRandomIntBetween(1,9);
            if(divisor == 1){
                dividend = RandomUtil.generateRandomIntBetween(10,99);
                divisor = RandomUtil.generateRandomIntBetween(1,9);
            }
        }

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

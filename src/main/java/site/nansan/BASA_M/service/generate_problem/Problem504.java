package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Carry;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.dto.*;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 네자리수 뺄셈 */
@Service
public class Problem504 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int operator1 = RandomUtil.generateRandomNDigitInt(4);
        int operator2 = RandomUtil.generateRandomNDigitInt(4);
        while(operator1 < operator2){
            operator2 = RandomUtil.generateRandomNDigitInt(4);
        }

        return GeneratedProblemResponse.builder()
                .problem(Problem.builder()
                        .first(operator1)
                        .second(operator2)
                        .operator(Operator.MIN)
                        .build())
                .answer(Answer.builder()
                        .result(Result.from(operator1-operator2))
                        .carry1(Carry.calculateSubtractionBorrow(operator1,operator2))
                        .build())
                .build();
    }
}

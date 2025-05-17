package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.Problem;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Carry;
import site.nansan.BASA_M.domain.answer.Result;
import site.nansan.BASA_M.dto.*;
import site.nansan.BASA_M.global.util.RandomUtil;

/** 받아올림 있는 세자리 + 세자리 */
@Service
public class Problem403 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int a = RandomUtil.generateRandomIntBetween(100,700);
        int a_h = a / 100;
        int a_t = (a / 10) % 10;
        int a_o = a % 10;

        int b = RandomUtil.generateRandomIntBetween(100,600);
        int b_h = b / 100;
        int b_t = (b / 10) % 10;
        int b_o = b % 10;

        while(a_h + b_h<10 && a_t + b_t <10 && a_o + b_o<10){
            b = RandomUtil.generateRandomIntBetween(100,600);
            b_h = b / 100;
            b_t = (b / 10) % 10;
            b_o = b % 10;
        }
        int sum = a + b;

        return GeneratedProblemResponse.builder()
                .problem(Problem.builder()
                        .first(a)
                        .second(b)
                        .operator(Operator.PLUS)
                        .build())
                .answer(Answer.builder()
                        .result(Result.from(sum))
                        .carry1(Carry.calculateAdditionCarry(a,b))
                        .build())
                .build();
    }
}

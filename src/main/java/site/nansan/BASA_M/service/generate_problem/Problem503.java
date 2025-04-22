package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.*;
import site.nansan.BASA_M.util.RandomUtil;

/** 네자리 수 덧셈 */
@Service
public class Problem503 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int operator1 = RandomUtil.generateRandomNDigitInt(4);
        int operator2 = RandomUtil.generateRandomNDigitInt(4);

        return GeneratedProblemResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(operator1)
                        .second(operator2)
                        .operator(Operator.PLUS)
                        .build())
                .answer(AnswerDTO.builder()
                        .result(ResultDTO.from(operator1+operator2))
                        .carry1(CarryDTO.calculateAdditionCarry(operator1,operator2))
                        .build())
                .build();
    }
}

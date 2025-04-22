package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.*;
import site.nansan.BASA_M.util.RandomUtil;

/** 10 - 몇의 뺄셈 */
@Service
public class Problem104 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        final int fixedOperand = 10;
        int operand2 = RandomUtil.generateRandomNDigitInt(1);
        int result = fixedOperand - operand2;

        return GeneratedProblemResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(fixedOperand)
                        .second(operand2)
                        .operator(Operator.MIN)
                        .build())
                .answer(AnswerDTO.builder()
                        .result(ResultDTO.from(result))
                        .carry1(CarryDTO.calculateSubtractionBorrow(fixedOperand, operand2))
                        .build())
                .build();
    }
}

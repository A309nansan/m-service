package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.AnswerDTO;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.dto.ProblemDTO;
import site.nansan.BASA_M.dto.ResultDTO;
import site.nansan.BASA_M.util.RandomUtil;

/** 받아올림 없는 두자리 + 두자리 */
@Service
public class Problem203 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {
        int operand1 = RandomUtil.generateRandomIntBetween(10, 67);
        if(operand1 % 10 == 9 || operand1 % 10 == 8 || operand1 % 10 == 7)
            operand1 = RandomUtil.generateRandomIntBetween(10, 67);
        int tensPart = RandomUtil.generateRandomIntBetween(1, 10 - (operand1 / 10) - 1);
        int onesPart = RandomUtil.generateRandomIntBetween(0, 10 - (operand1 % 10) - 1);
        int operand2 = tensPart * 10 + onesPart;
        int sum = operand1 + operand2;

        return GeneratedProblemResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(operand1)
                        .second(operand2)
                        .operator(Operator.PLUS)
                        .build())
                .answer(AnswerDTO.builder()
                        .result(ResultDTO.from(sum))
                        .build())
                .build();
    }
}

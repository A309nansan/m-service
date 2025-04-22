package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.AnswerDTO;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.dto.ProblemDTO;
import site.nansan.BASA_M.dto.ResultDTO;
import site.nansan.BASA_M.util.RandomUtil;

/** 한자리 수 끼리의 뺄셈 ( 정답 :1 이상 ) */
@Service
public class Problem102 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int operand1 = RandomUtil.generateRandomIntBetween(2, 9);
        if(operand1 == 2) {
            operand1 = RandomUtil.generateRandomIntBetween(2, 9);
        }
        int operand2 = RandomUtil.generateRandomNDigitIntLessThan(1, operand1 - 1);
        int diff = operand1 - operand2;

        return GeneratedProblemResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(operand1)
                        .second(operand2)
                        .operator(Operator.MIN)
                        .build())
                .answer(AnswerDTO.builder()
                        .result(ResultDTO.from(diff))
                        .build())
                .build();
    }
}

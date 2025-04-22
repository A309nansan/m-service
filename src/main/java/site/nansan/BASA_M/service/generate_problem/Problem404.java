package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.*;
import site.nansan.BASA_M.util.RandomUtil;

/** 받아내림 있는 세자리 - 세자리 */
@Service
public class Problem404 extends ProblemGenerationService{

    @Override
    public GeneratedProblemResponse makeProblem() {

        int operand1 = RandomUtil.generateRandomIntBetween(300, 977);
        while ((operand1 / 10) % 10 == 9 && operand1 % 10 == 9) {
            operand1 = RandomUtil.generateRandomIntBetween(300, 977);
        }
        int aTens = (operand1 / 10) % 10;
        int aOnes = operand1 % 10;
        int bHundreds = RandomUtil.generateRandomIntBetween(1, operand1 / 100 - 1);
        int bTens = RandomUtil.generateRandomIntBetween(1, 9);
        int bOnes = RandomUtil.generateRandomIntBetween(1, 9);
        while (aTens >= bTens && aOnes >= bOnes) {
            bTens = RandomUtil.generateRandomIntBetween(1, 9);
            bOnes = RandomUtil.generateRandomIntBetween(1, 9);
        }
        int operand2 = bHundreds * 100 + bTens * 10 + bOnes;
        int diff = operand1 - operand2;

        return GeneratedProblemResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(operand1)
                        .second(operand2)
                        .operator(Operator.MIN)
                        .build())
                .answer(AnswerDTO.builder()
                        .result(ResultDTO.from(diff))
                        .carry1(CarryDTO.calculateSubtractionBorrow(operand1, operand2))
                        .build())
                .build();
    }
}

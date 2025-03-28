package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.dto.answer.AnswerDTO;
import site.nansan.BASA_M.dto.answer.CarryDTO;
import site.nansan.BASA_M.dto.answer.ResultDTO;
import site.nansan.BASA_M.dto.problem.ProblemDTO;
import site.nansan.BASA_M.util.RandomUtil;

@Service
public class M1ProblemGenerationService {

    /** 합이 9 이하인 덧셈 */
    public GeneratedProblemResponse generateM1_1Problem() {
        // 1과 8은 경우의 수가 1개 밖에 없으니 가중치를 둠
        int operand1 = RandomUtil.generateRandomIntBetween(1, 8);
        if(operand1 == 1 || operand1 == 8) {
            operand1 = RandomUtil.generateRandomIntBetween(1, 8);
        }
        int operand2 = RandomUtil.generateRandomNDigitIntLessThan(1, 9 - operand1);
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

    /** 한자리 수 끼리의 뺄셈 ( 정답 :1 이상 ) */
    public GeneratedProblemResponse generateM1_2Problem() {
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

    /** 일의 자리 수 2개의 합이 10 이상인 덧셈 */
    public GeneratedProblemResponse generateM1_3Problem() {
        int operand1 = RandomUtil.generateRandomNDigitInt(1);
        if(operand1 == 1) {
            operand1 = RandomUtil.generateRandomIntBetween(2, 9);
        }
        int operand2 = RandomUtil.generateRandomIntBetween(10 - operand1, 9);
        int sum = operand1 + operand2;

        return GeneratedProblemResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(operand1)
                        .second(operand2)
                        .operator(Operator.PLUS)
                        .build())
                .answer(AnswerDTO.builder()
                        .result(ResultDTO.from(sum))
                        .carry1(CarryDTO.calculateAdditionCarry(operand1, operand2))
                        .build())
                .build();
    }

    /** 10 - 몇의 뺄셈 */
    public GeneratedProblemResponse generateM1_4Problem() {
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

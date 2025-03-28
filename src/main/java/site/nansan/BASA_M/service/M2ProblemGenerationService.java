package site.nansan.BASA_M.service;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.dto.answer.AnswerDTO;
import site.nansan.BASA_M.dto.answer.ResultDTO;
import site.nansan.BASA_M.dto.problem.ProblemDTO;
import site.nansan.BASA_M.util.RandomUtil;

@Service
public class M2ProblemGenerationService {

    /** 받아올림 없는 두자리 + 한자리 */
    public GeneratedProblemResponse generateM2_1Problem() {
        int operand1 = RandomUtil.generateRandomNDigitInt(2);
        while(operand1 % 10 == 9) {
            operand1 = RandomUtil.generateRandomNDigitInt(2);
            if(operand1 % 10 == 8 || operand1 % 10 == 7) {
                operand1 = RandomUtil.generateRandomNDigitInt(2);
            }
        }
        int operand2 = RandomUtil.generateRandomNDigitIntLessThan(1, 9 - (operand1 % 10));
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

    /** 받아내림 없는 두자리 - 한자리 */
    public GeneratedProblemResponse generateM2_2Problem() {
        int operand1 = RandomUtil.generateRandomNDigitInt(2);
        while(operand1 % 10 == 0) {
            operand1 = RandomUtil.generateRandomNDigitInt(2);
            if(operand1 % 10 == 1 || operand1 % 10 == 2) {
                operand1 = RandomUtil.generateRandomNDigitInt(2);
            }
        }
        int operand2 = RandomUtil.generateRandomNDigitIntLessThan(1, operand1 % 10);
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

    /** 받아올림 없는 두자리 + 두자리 */
    public GeneratedProblemResponse generateM2_3Problem() {
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

    /** 받아내림 없는 두자리 - 두자리 */
    public GeneratedProblemResponse generateM2_4Problem() {
        int operand1 = RandomUtil.generateRandomIntBetween(23, 99);
        if(operand1 % 10 == 0 || operand1 % 10 == 1 || operand1 % 10 == 2){
            operand1 = RandomUtil.generateRandomIntBetween(23, 99);
        }
        int tensPart = RandomUtil.generateRandomIntBetween(1, operand1 / 10);
        int onesPart = RandomUtil.generateRandomIntBetween(0, operand1 % 10);
        int operand2 = tensPart * 10 + onesPart;
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

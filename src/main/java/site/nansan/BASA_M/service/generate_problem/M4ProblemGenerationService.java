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
public class M4ProblemGenerationService {
    /** 받아올림 없는 세자리 + 세자리 */
    public GeneratedProblemResponse generateM4_1Problem() {
        int hundreds = RandomUtil.generateRandomIntBetween(1, 6);
        int tens = RandomUtil.generateRandomIntBetween(0, 7);
        int ones = RandomUtil.generateRandomIntBetween(0, 7);
        int operand1 = hundreds * 100 + tens * 10 + ones;

        int bHundreds = RandomUtil.generateRandomIntBetween(1, 9 - hundreds);
        int bTens = RandomUtil.generateRandomIntBetween(0, 9 - tens);
        int bOnes = RandomUtil.generateRandomIntBetween(0, 9 - ones);
        int operand2 = bHundreds * 100 + bTens * 10 + bOnes;

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

    /** 받아내림 없는 세자리 - 세자리 */
    public GeneratedProblemResponse generateM4_2Problem() {
        int aHundreds = RandomUtil.generateRandomIntBetween(1, 9);
        int aTens = RandomUtil.generateRandomIntBetween(0, 9);
        int aOnes = RandomUtil.generateRandomIntBetween(0, 9);
        int operand1 = aHundreds * 100 + aTens * 10 + aOnes;

        int bHundreds = RandomUtil.generateRandomIntBetween(1, aHundreds);
        int bTens = RandomUtil.generateRandomIntBetween(0, aTens);
        int bOnes = RandomUtil.generateRandomIntBetween(0, aOnes);
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
                        .build())
                .build();
    }

    /** 받아올림 있는 세자리 + 세자리 */
    public GeneratedProblemResponse generateM4_3Problem() {
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
                .problem(ProblemDTO.builder()
                        .first(a)
                        .second(b)
                        .operator(Operator.PLUS)
                        .build())
                .answer(AnswerDTO.builder()
                        .result(ResultDTO.from(sum))
                        .carry1(CarryDTO.calculateAdditionCarry(a,b))
                        .build())
                .build();
    }

    /** 받아내림 있는 세자리 - 세자리 */
    public GeneratedProblemResponse generateM4_4Problem() {
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

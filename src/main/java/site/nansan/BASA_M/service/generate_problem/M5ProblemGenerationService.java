package site.nansan.BASA_M.service.generate_problem;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.dto.AnswerDTO;
import site.nansan.BASA_M.dto.CarryDTO;
import site.nansan.BASA_M.dto.ResultDTO;
import site.nansan.BASA_M.dto.ProblemDTO;
import site.nansan.BASA_M.util.RandomUtil;

@Service
public class M5ProblemGenerationService {
    /** 구구단을 이용한 나눗셈의 몫 구하기 */
    public GeneratedProblemResponse generateM5_1Problem() {
        int multiplicand = RandomUtil.generateRandomIntBetween(1, 9);
        int multiplier = RandomUtil.generateRandomIntBetween(1, 9);
        int dividend = multiplicand * multiplier;

        return GeneratedProblemResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(dividend)
                        .second(multiplier)
                        .operator(Operator.DIV)
                        .build())
                .answer(AnswerDTO.builder()
                        .result(ResultDTO.from(dividend / multiplier))
                        .build())
                .build();
    }

    /** 두자리 수 한자리 수 곱셈 */
    public GeneratedProblemResponse generateM5_2Problem() {
        int multiplicand = RandomUtil.generateRandomNDigitInt(2);
        int multiplier = RandomUtil.generateRandomNDigitInt(1);

        return GeneratedProblemResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(multiplicand)
                        .second(multiplier)
                        .operator(Operator.MULT)
                        .build())
                .answer(AnswerDTO.calculateOneDigitMultiplication(multiplicand, multiplier, true))
                .build();
    }

    /** 네자리 수 덧셈 */
    public GeneratedProblemResponse generateM5_3Problem() {
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

    /** 네자리수 뺄셈 */
    public GeneratedProblemResponse generateM5_4Problem() {
        int operator1 = RandomUtil.generateRandomNDigitInt(4);
        int operator2 = RandomUtil.generateRandomNDigitInt(4);
        while(operator1 < operator2){
            operator2 = RandomUtil.generateRandomNDigitInt(4);
        }

        return GeneratedProblemResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(operator1)
                        .second(operator2)
                        .operator(Operator.MIN)
                        .build())
                .answer(AnswerDTO.builder()
                        .result(ResultDTO.from(operator1-operator2))
                        .carry1(CarryDTO.calculateSubtractionBorrow(operator1,operator2))
                        .build())
                .build();
    }
}

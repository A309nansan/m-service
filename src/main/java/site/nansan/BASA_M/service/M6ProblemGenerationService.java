package site.nansan.BASA_M.service;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.CalculationResponse;
import site.nansan.BASA_M.dto.answer.AnswerDTO;
import site.nansan.BASA_M.dto.problem.ProblemDTO;
import site.nansan.BASA_M.util.RandomUtil;

@Service
public class M6ProblemGenerationService {
    /** 세자리 * 한자리 */
    public CalculationResponse generateM6_1Problem() {
        int multiplicand = RandomUtil.generateRandomNDigitInt(3);
        int multiplier = RandomUtil.generateRandomNDigitInt(1);

        return CalculationResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(multiplicand)
                        .second(multiplier)
                        .operator(Operator.MULT)
                        .build())
                .answer(AnswerDTO.calculateOneDigitMultiplication(multiplicand, multiplier))
                .build();
    }

    /** 두자리 * 두자리 */
    public CalculationResponse generateM6_2Problem() {
        int multiplicand = RandomUtil.generateRandomNDigitInt(2);
        int multiplier = RandomUtil.generateRandomNDigitInt(2);

        return CalculationResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(multiplicand)
                        .second(multiplier)
                        .operator(Operator.MULT)
                        .build())
                .answer(AnswerDTO.calculateTwoDigitMultiplication(multiplicand, multiplier))
                .build();
    }

    /** 나머지가 없는 두자리수 나누기 한자리수 */
    public CalculationResponse generateM6_3Problem() {
        int dividend = RandomUtil.generateRandomIntBetween(10,99);
        int divisor = RandomUtil.generateRandomIntBetween(1,9);
        while(dividend % divisor != 0){
            dividend = RandomUtil.generateRandomIntBetween(10,99);
            divisor = RandomUtil.generateRandomIntBetween(1,9);
            if(divisor == 1){
                dividend = RandomUtil.generateRandomIntBetween(10,99);
                divisor = RandomUtil.generateRandomIntBetween(1,9);
            }
        }

        return CalculationResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(dividend)
                        .second(divisor)
                        .operator(Operator.DIV)
                        .build())
                .answer(AnswerDTO.calculateDivision(dividend,divisor))
                .build();
    }

    /** 나머지가 있는 두자리수 / 한자리수 */
    public CalculationResponse generateM6_4Problem() {
        int dividend = RandomUtil.generateRandomIntBetween(10,99);
        int divisor = RandomUtil.generateRandomIntBetween(1,9);
        while(dividend % divisor == 0){
            dividend = RandomUtil.generateRandomIntBetween(10,99);
            divisor = RandomUtil.generateRandomIntBetween(1,9);
            if(divisor == 1){
                dividend = RandomUtil.generateRandomIntBetween(10,99);
                divisor = RandomUtil.generateRandomIntBetween(1,9);
            }
        }

        return CalculationResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(dividend)
                        .second(divisor)
                        .operator(Operator.DIV)
                        .build())
                .answer(AnswerDTO.calculateDivision(dividend, divisor))
                .build();
    }

    /** 나머지가 없는 세자리수 / 한자리수*/
    public CalculationResponse generateM6_5Problem() {
        int dividend = RandomUtil.generateRandomIntBetween(10,99);
        int divisor = RandomUtil.generateRandomIntBetween(1,9);
        if(divisor==1)
            divisor = RandomUtil.generateRandomIntBetween(1,9);
        dividend = dividend * divisor;

        return CalculationResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(dividend)
                        .second(divisor)
                        .operator(Operator.DIV)
                        .build())
                .answer(AnswerDTO.calculateDivision(dividend, divisor))
                .build();
    }

    /** 나머지가 있는 세 자리수 / 한 자리수 */
    public CalculationResponse generateM6_6Problem() {
        int dividend = RandomUtil.generateRandomIntBetween(10,99);
        int divisor = RandomUtil.generateRandomIntBetween(2,9);
        dividend = dividend * divisor;
        dividend += RandomUtil.generateRandomIntBetween(1,divisor-1);

        return CalculationResponse.builder()
                .problem(ProblemDTO.builder()
                        .first(dividend)
                        .second(divisor)
                        .operator(Operator.DIV)
                        .build())
                .answer(AnswerDTO.calculateDivision(dividend,divisor))
                .build();
    }
}

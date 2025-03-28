package site.nansan.BASA_M.service;

import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.GeneratedProblemResponse;
import site.nansan.BASA_M.dto.answer.AnswerDTO;
import site.nansan.BASA_M.dto.answer.CarryDTO;
import site.nansan.BASA_M.dto.answer.ResultDTO;
import site.nansan.BASA_M.dto.problem.ProblemDTO;
import site.nansan.BASA_M.util.RandomUtil;

@Service
public class M3ProblemGenerationService {

    /** 받아올림 있는 두자리 + 한자리 */
    public GeneratedProblemResponse generateM3_1Problem() {
        int operand1 = RandomUtil.generateRandomNDigitInt(2);
        while(operand1 % 10 == 0){
            operand1 = RandomUtil.generateRandomNDigitInt(2);
            if(operand1 % 10 == 1 || operand1 % 10 == 2){
                operand1 = RandomUtil.generateRandomNDigitInt(2);
            }
        }
        int operand2 = RandomUtil.generateRandomIntBetween(10 - (operand1 % 10), 9);
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

    /** 받아내림 있는 두자리 - 한자리 */
    public GeneratedProblemResponse generateM3_2Problem() {
        int operand1 = RandomUtil.generateRandomNDigitInt(2);
        while(operand1 % 10 == 9){
            operand1 = RandomUtil.generateRandomNDigitInt(2);
            if(operand1 % 10 == 8 || operand1 % 10 == 7){
                operand1 = RandomUtil.generateRandomNDigitInt(2);
            }
        }
        int operand2 = RandomUtil.generateRandomIntBetween((operand1 % 10) + 1, 9);
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

    /** 받아올림 있는 두자리 + 두자리 */
    public GeneratedProblemResponse generateM3_3Problem() {
        int operand1 = RandomUtil.generateRandomIntBetween(10, 87);
        while(operand1 % 10 == 0){
            operand1 = RandomUtil.generateRandomIntBetween(10, 87);
            if(operand1 % 10 == 1 || operand1 % 10 == 2){
                operand1 = RandomUtil.generateRandomIntBetween(10, 87);
            }
        }
        int tensPart = RandomUtil.generateRandomNDigitInt(1);
        int onesPart = RandomUtil.generateRandomIntBetween(10 - (operand1 % 10), 9);
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
                        .carry1(CarryDTO.calculateAdditionCarry(operand1, operand2))
                        .build())
                .build();
    }


    /** 받아내림 있는 두자리 - 두자리 */
    public GeneratedProblemResponse generateM3_4Problem() {
        int operand1 = RandomUtil.generateRandomIntBetween(20, 97);
        while(operand1 % 10 == 9){
            operand1 = RandomUtil.generateRandomIntBetween(20, 97);
            if(operand1 % 10 == 8 || operand1 % 10 == 7){
                operand1 = RandomUtil.generateRandomIntBetween(20, 97);
            }
        }
        int tensPart = RandomUtil.generateRandomIntBetween(1, operand1 / 10 - 1);
        int onesPart = RandomUtil.generateRandomIntBetween((operand1 % 10) + 1, 9);
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
                        .carry1(CarryDTO.calculateSubtractionBorrow(operand1, operand2))
                        .build())
                .build();
    }
}

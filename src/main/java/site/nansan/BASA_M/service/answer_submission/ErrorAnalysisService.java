package site.nansan.BASA_M.service.answer_submission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.domain.ProblemErrorCode;
import site.nansan.BASA_M.domain.answer.Answer;
import site.nansan.BASA_M.domain.answer.Carry;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;

import java.util.EnumSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ErrorAnalysisService {
    public Set<ProblemErrorCode> findCause(AnswerSubmissionRequest request, int group, int child){

        int operand1 = request.getGeneratedProblem().getFirst();
        int operand2 = request.getGeneratedProblem().getSecond();
        Operator operator = request.getGeneratedProblem().getOperator();

        Answer generatedAnswer = request.getGeneratedAnswer();
        Answer userAnswer = request.getUserAnswer();
        int submittedResult = request.getUserAnswer().getResult().toNumber();

        Set<ProblemErrorCode> errorCodes = EnumSet.noneOf(ProblemErrorCode.class);

        isIncorrectDueToOperation(operand1, operand2, operator, submittedResult, errorCodes, group, child);

        if (isLargerMinusSmallerOnly(operand1, operand2, operator, submittedResult)) {
            errorCodes.add(ProblemErrorCode.LARGER_MINUS_SMALLER);
        }
        if (isAlgorithmMixedError(operand1, operand2, operator, submittedResult)) {
            errorCodes.add(ProblemErrorCode.ALGORITHM_MIXED_ERROR);
        }
        if (isCarryError(operand1, operand2, operator, generatedAnswer, userAnswer, submittedResult)) {
            if(operator == Operator.PLUS){
                errorCodes.add(ProblemErrorCode.CARRY_ERROR_1);
            } else if(operator == Operator.MIN){
                errorCodes.add(ProblemErrorCode.CARRY_ERROR_2);
            } else if(operator == Operator.MULT){
                errorCodes.add(ProblemErrorCode.CARRY_ERROR_3);
            }
        }
        if(isWrongPosition(generatedAnswer.getResult().toNumber(), submittedResult)){
            errorCodes.add(ProblemErrorCode.WRONG_POSITION);
        }
        if(errorCodes.isEmpty()){
            errorCodes.add(ProblemErrorCode.CALCULATE_WRONG);
        }

        return errorCodes;
    }

    /**
     * 오류 유형 1
     * 덧셈문제를 뺄셈문제로 해결
     * 덧셈문제를 곱셈문제로 해결
     * 뺄셈문제를 덧셈문제로 해결
     * 뺄셈문제를 곱셈문제로 해결
     * 곱셈 문제를 덧셈 문제로 해결
     * 곱셈 문제를 뺄셈 문제로 해결
     * */
    private void isIncorrectDueToOperation(int operand1, int operand2, Operator operator, int submittedResult, Set<ProblemErrorCode> errorCodes, int group, int child) {
        if(operator == Operator.PLUS && submittedResult == operand1 - operand2){
            errorCodes.add(ProblemErrorCode.INCORRECT_OPERATION_1);
        } else if(operator == Operator.PLUS && submittedResult == operand1 * operand2) {
            errorCodes.add(ProblemErrorCode.INCORRECT_OPERATION_2);
        } else if(operator == Operator.MIN && submittedResult == operand1 + operand2) {
            errorCodes.add(ProblemErrorCode.INCORRECT_OPERATION_3);
        } else if(operator == Operator.MIN && submittedResult == operand1 * operand2) {
            errorCodes.add(ProblemErrorCode.INCORRECT_OPERATION_4);
        } else if(operator == Operator.MULT && submittedResult == operand1 + operand2) {
            if(group == 5){
                errorCodes.add(ProblemErrorCode.INCORRECT_OPERATION_5_1);
            } else {
                errorCodes.add(ProblemErrorCode.INCORRECT_OPERATION_5_2);
            }
        } else if( operator == Operator.MULT && submittedResult == operand1 - operand2) {
            if(group == 5){
                errorCodes.add(ProblemErrorCode.INCORRECT_OPERATION_6_1);
            } else {
                errorCodes.add(ProblemErrorCode.INCORRECT_OPERATION_6_2);
            }
        }
    }

    /**
     * 오류 유형 3
     * 무조건 큰 수에서 작은 수를 빼는 오류
     */
    public boolean isLargerMinusSmallerOnly(int operand1, int operand2, Operator operator, int submittedResult) {
        if (operator != Operator.MIN) {
            return false;
        }

        String sOp1 = String.format("%0" + Math.max(String.valueOf(operand1).length(), String.valueOf(operand2).length()) + "d", operand1);
        String sOp2 = String.format("%0" + Math.max(String.valueOf(operand1).length(), String.valueOf(operand2).length()) + "d", operand2);
        String sUser = String.format("%0" + sOp1.length() + "d", Math.abs(submittedResult));

        for (int i = sOp1.length() - 1; i >= 0; i--) {
            int digit1 = sOp1.charAt(i) - '0';
            int digit2 = sOp2.charAt(i) - '0';
            int userDigit = sUser.charAt(i) - '0';
            if (digit1 < digit2) {
                if (userDigit == (digit2 - digit1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 오류 유형 4
     * 덧셈, 뺼셈에 곱셈처럼 알고리즘을 섞어서 하기
     */
    public boolean isAlgorithmMixedError(int operand1, int operand2, Operator operator, int submittedResult) {
        if(operator != Operator.PLUS) return false;
        if(operand1 <=9) return false;
        if(operand2 >= 10) return false;

        return isAdditionAlgorithmMixedError(operand1, operand2, submittedResult);

        // 뺼셈 로직 제거
//        boolean erroneousAdditionResult = false;
//        boolean erroneousSubtractionResult = false;
//
//
//        if(operator == Operator.PLUS){
//            erroneousAdditionResult = isAdditionAlgorithmMixedError(operand1, operand2, submittedResult);
//        }
//        else {
//            erroneousSubtractionResult = isSubtractionAlgorithmMixedError(operand1, operand2, submittedResult);
//        }
//
//        return erroneousAdditionResult || erroneousSubtractionResult;
    }
    private boolean isAdditionAlgorithmMixedError(int operator1, int operator2, int submittedResult) {
        int remaining = operator1;
        int multiplier = 1;
        int erroneousResult = 0;
        int carry = 0;

        while (remaining > 0) {
            int currentDigit = remaining % 10;
            remaining /= 10;
            int sum = currentDigit + operator2 + carry;
            int newDigit = sum % 10;
            carry = sum / 10;
            erroneousResult += newDigit * multiplier;
            multiplier *= 10;
        }
        if (carry > 0) {
            erroneousResult += carry * multiplier;
        }

        return erroneousResult == submittedResult;
    }

    private boolean isSubtractionAlgorithmMixedError(int operator1, int operator2, int submittedResult) {
        int erroneousResult = 0;
        int multiplier = 1;
        while(operator1 > 0) {
            int tempOperator1 = operator1 % 10;
            operator1 /=10;
            int digitDiff;
            if (tempOperator1 < operator2) {
                digitDiff = operator2 - tempOperator1;
            } else {
                digitDiff = tempOperator1 - operator2;
            }
            erroneousResult += digitDiff * multiplier;
            multiplier *= 10;
        }

        return erroneousResult == submittedResult;
    }

    /**
     * 오류 유형 5
     * carry 틀림 || carry 맞지만 계산을 안함
     */
    public boolean isCarryError(int operand1, int operand2, Operator operator, Answer generatedAnswer, Answer userAnswer, int submittedResult) {
        if(operator == Operator.DIV)
            return false;

        return isCarryWrong(generatedAnswer, userAnswer) || isCarryNotCalculated(operand1, operand2, operator, submittedResult, userAnswer.getCarry1());
    }

    private boolean isCarryWrong(Answer generatedAnswer, Answer userAnswer){
        if (generatedAnswer.getCarry1() != null && userAnswer.getCarry1() != null) {
            return !generatedAnswer.getCarry1().equals(userAnswer.getCarry1());
        }
        if (generatedAnswer.getCarry2() != null && userAnswer.getCarry2() != null) {
            return !generatedAnswer.getCarry2().equals(userAnswer.getCarry2());
        }

        return false;
    }

    private boolean isCarryNotCalculated(int operand1, int operand2, Operator operator, int submittedResult, Carry carry1) {
        if(carry1 != null && operator == Operator.PLUS && computeIsAdditionCarryNotCalculated(operand1, operand2, submittedResult)){
            return true;
        }
        else if(carry1 != null && operator == Operator.MIN && computeIsSubtractionCarryNotCalculated(operand1, operand2, carry1, submittedResult))
            return true;

        return false;
    }

    private boolean computeIsAdditionCarryNotCalculated(int operand1, int operand2, int submittedResult) {
        int carry = 0;

        while (operand1 > 0) {
            int currentOperand1 = operand1 % 10;
            int currentOperand2 = operand2 % 10;
            int currentSubmitResult = submittedResult % 10;
            operand1 /= 10;
            operand2 /= 10;
            submittedResult/=10;

            if((currentOperand1 + currentOperand2) % 10 == currentSubmitResult && carry != 0)
                return true;

            int sum = currentOperand1 + currentOperand2 + carry;
            carry = sum / 10;
        }
        return false;
    }

    private boolean computeIsSubtractionCarryNotCalculated(int operand1, int operand2, Carry carry, int submittedResult) {
        if(carry.getTwo() != null) {
            int currentOperand1 = (operand1 / 10) % 10;
            int currentOperand2 = (operand2 / 10) % 10;
            int currentSubmitResult = (submittedResult / 10) % 10;

            if(currentOperand2 > currentOperand1) {
                currentOperand1 += 10;
            }

            if(currentOperand1 - currentOperand2 == currentSubmitResult)
                return true;
        }
        if(carry.getThree() != null){
            int currentOperand1 = (operand1 / 100) % 10;
            int currentOperand2 = (operand2 / 100) % 10;
            int currentSubmitResult = (submittedResult / 100) % 10;

            if(currentOperand2 > currentOperand1) {
                currentOperand1 += 10;
            }

            if(currentOperand1 - currentOperand2 == currentSubmitResult)
                return true;
        }
        if(carry.getFour() != null){
            int currentOperand1 = (operand1 / 1000) % 10;
            int currentOperand2 = (operand2 / 1000) % 10;
            int currentSubmitResult = (submittedResult / 1000) % 10;

            if(currentOperand2 > currentOperand1) {
                currentOperand1 += 10;
            }

            if(currentOperand1 - currentOperand2 == currentSubmitResult)
                return true;
        }
        return false;
    }

    /**
     * 오류 6 : 잘못된 자리에 답을 쓰는 경우
     */
    private boolean isWrongPosition(int generatedAnswer, int userAnswer) {
        int[] digitCounts = new int[10];
        int operator1 = generatedAnswer;
        int operator2 = userAnswer;

        while(operator1 > 0){
            digitCounts[operator1 % 10]++;
            operator1 /= 10;
        }
        while(operator2 > 0){
            digitCounts[operator2 % 10]--;
            operator2 /=10;
        }

        for (int count : digitCounts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}

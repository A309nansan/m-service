package site.nansan.BASA_M.service.answer_submission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.nansan.BASA_M.domain.Operator;
import site.nansan.BASA_M.dto.AnswerSubmissionRequest;
import site.nansan.BASA_M.dto.answer.AnswerDTO;
import site.nansan.BASA_M.dto.answer.CarryDTO;

@Service
@RequiredArgsConstructor
public class ErrorAnalysisService {
    public void findCause(AnswerSubmissionRequest request){
        int operand1 = request.getGeneratedProblem().getFirst();
        int operand2 = request.getGeneratedProblem().getSecond();
        Operator operator = request.getGeneratedProblem().getOperator();

        AnswerDTO generatedAnswer = request.getGeneratedAnswer();
        AnswerDTO userAnswer = request.getUserAnswer();
        int submittedResult = request.getUserAnswer().getResult().toNumber();

        // 잘못된 연산 - 다른 연산 방법으로 문제 해결
        boolean isCauseOfIncorrectOperation = isIncorrectDueToOperation(operand1, operand2, operator, submittedResult);
        // 결함있는 알고리즘 - 큰 수에서 작은 수를 빼는 오류
        boolean isAlwaysSubtractingLargerFromSmaller = isLargerMinusSmallerOnly(operand1, operand2, operator, submittedResult);
        // 결함있는 알고리즘 - 덧셈에 곱셈처럼 알고리즘 섞기
        boolean isAdditionAlgorithmMixedError = isAlgorithmMixedError(operand1, operand2, operator, submittedResult);
        // 받아올림과 받아내림 오류
        boolean isCarryError = error5(operand1, operand2, operator, generatedAnswer, userAnswer, submittedResult);
        // 잘못된 자리에 답을 쓰는 경우
        boolean isWrongPosition = isWrongPosition(generatedAnswer.getResult().toNumber(), submittedResult);
        // 계산산의 오류 - 받아 올림은 맞는데 계산실수
        boolean hasCarryConceptButWrongAnswer = !isCauseOfIncorrectOperation && !isAlwaysSubtractingLargerFromSmaller && !isAdditionAlgorithmMixedError && !isCarryError && !isWrongPosition;

        System.out.println("잘못된 연산 - 다른 연산 방법으로 문제 해결: "+ isCauseOfIncorrectOperation);
        System.out.println("계산산의 오류 : "+hasCarryConceptButWrongAnswer);
        System.out.println("결함있는 알고리즘 - 큰 수에서 작은 수를 빼는 오류 : "+isAlwaysSubtractingLargerFromSmaller);
        System.out.println("결함있는 알고리즘 - 덧셈에 곱셈처럼 알고리즘 섞기 : "+isAdditionAlgorithmMixedError);
        System.out.println("받아올림과 받아내림 오류 : "+isCarryError);
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
    private boolean isIncorrectDueToOperation(int operand1, int operand2, Operator operator, int submittedResult) {
        return operator == Operator.PLUS && submittedResult == operand1 - operand2 ||
                operator == Operator.PLUS && submittedResult == operand1 * operand2 ||
                operator == Operator.MIN && submittedResult == operand1 + operand2 ||
                operator == Operator.MIN && submittedResult == operand1 * operand2 ||
                operator == Operator.MULT && submittedResult == operand1 - operand2 ||
                operator == Operator.MULT && submittedResult == operand1 + operand2;
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
    public boolean error5(int operand1, int operand2, Operator operator, AnswerDTO generatedAnswer, AnswerDTO userAnswer, int submittedResult) {
        if(operator == Operator.DIV)
            return false;

        return isCarryWrong(generatedAnswer, userAnswer) || isCarryNotCalculated(operand1, operand2, operator, submittedResult, userAnswer.getCarry1());
    }

    private boolean isCarryWrong(AnswerDTO generatedAnswer, AnswerDTO userAnswer){
        if (generatedAnswer.getCarry1() != null) {
            return !generatedAnswer.getCarry1().equals(userAnswer.getCarry1());
        }
        if (generatedAnswer.getCarry2() != null) {
            return !generatedAnswer.getCarry2().equals(userAnswer.getCarry2());
        }

        return false;
    }

    private boolean isCarryNotCalculated(int operand1, int operand2, Operator operator, int submittedResult, CarryDTO carry1) {
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

    private boolean computeIsSubtractionCarryNotCalculated(int operand1, int operand2, CarryDTO carry, int submittedResult) {
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

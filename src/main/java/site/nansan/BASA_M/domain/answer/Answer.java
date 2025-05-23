package site.nansan.BASA_M.domain.answer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Answer {
    Result result;
    Carry carry2;
    Carry carry1;
    Calculate calculate1;
    Calculate calculate2;
    Calculate calculate3;
    Integer remainder;

    @JsonIgnore
    public int calculateAnswerScore(){
        int score = 0;
        if(result != null) score += result.getSize();
        if(calculate1 != null) score += calculate1.getSize();
        if(calculate2 != null) score += calculate2.getSize();
        if(calculate3 != null) score += calculate3.getSize();
        if(remainder != null) score += 1;

        return score;
    }

    public static Answer calculateOneDigitMultiplication(int a, int b, boolean isOnlyForOneDigitMultiplication) {
        int carryIdx = 1, calculateIdx = 0, currentCarry = 0;
        String aString = String.valueOf(a);

        Carry carry = new Carry();
        Calculate calculate = new Calculate();
        Result result = Result.from(a * b);

        for (int i = 0; i < aString.length(); i++) {
            int currentADigit = aString.charAt(aString.length() - i - 1) - '0';
            int ans = currentADigit * b + currentCarry;

            if (ans < 10) {
                currentCarry = 0;
                updateCalculate(calculate, calculateIdx, ans);
            } else {
                currentCarry = ans / 10;
                updateCalculate(calculate, calculateIdx, ans % 10);
                updateCarry(carry, carryIdx, currentCarry);
            }
            carryIdx++;
            calculateIdx++;
        }
        if(currentCarry!=0){
            updateCalculate(calculate, calculateIdx, currentCarry);
        }

        if(isOnlyForOneDigitMultiplication){
            return Answer.builder()
                    .carry1(carry.isNull() ? null : carry)
                    .result(result)
                    .build();
        }
        return Answer.builder()
                .carry1(carry.isNull() ? null : carry)
                .calculate1(calculate)
                .result(result)
                .build();
    }

    public static Answer calculateTwoDigitMultiplication(int a, int b) {
        Answer answer = calculateOneDigitMultiplication(a,b%10, false);

        int carryIdx = 2, calculateIdx = 1, currentCarry = 0;
        String aString = String.valueOf(a);

        Carry carry = new Carry();
        Calculate calculate = new Calculate();
        Result result = Result.from(a * b);
        b/=10;

        for (int i = 0; i < aString.length(); i++) {
            int currentADigit = aString.charAt(aString.length() - i - 1) - '0';
            int ans = currentADigit * b + currentCarry;

            if (ans < 10) {
                currentCarry = 0;
                updateCalculate(calculate, calculateIdx, ans);
            } else {
                currentCarry = ans / 10;
                updateCalculate(calculate, calculateIdx, ans % 10);
                updateCarry(carry, carryIdx, currentCarry);
            }
            carryIdx++;
            calculateIdx++;
        }
        if(currentCarry!=0){
            updateCalculate(calculate, calculateIdx, currentCarry);
        }

        return Answer.builder()
                .carry1(answer.getCarry1())
                .carry2(carry)
                .calculate1(answer.getCalculate1())
                .calculate2(calculate)
                .result(result)
                .build();
    }
    public static Answer calculateDivision(int a, int b){
        if(a / b < 10){
            return calculateLessThenTenDivision(a,b);
        }
        Calculate calculate1 = new Calculate();
        Calculate calculate2 = new Calculate();
        Calculate calculate3 = new Calculate();
        Integer remainNumber = null;
        Result result = Result.from(a / b);

        int baseNumber = a;
        int lineOneNumber=0;
        if(a >= 100){
            // 앞 두 숫자 구하기. a가 100 미만이기 때문에 첫 번째 자리에서 한번에 나뉘는 경우는 생략해도됨
            baseNumber = (a / 100 * 10) + (a / 10 % 10);
            lineOneNumber = baseNumber / b * b;
            int lineOneDigitThree = lineOneNumber / 10;
            int lineOneDigitTwo = lineOneNumber % 10;
            calculate1.setThree(lineOneDigitThree);
            calculate1.setTwo(lineOneDigitTwo);
        } else {
            baseNumber = a / 10;
            lineOneNumber = baseNumber / b * b;
            calculate1.setTwo(lineOneNumber);
        }
        int lineTwoNumber = a-(lineOneNumber * 10);
        if(lineTwoNumber == 0){
            remainNumber = 0;
        }
        else if(lineTwoNumber < 10){
            calculate2.setOne(lineTwoNumber);

            int lineThreeNumber = lineTwoNumber / b * b;
            calculate3.setOne(lineThreeNumber);

            if(lineTwoNumber-lineThreeNumber != 0){
                remainNumber = lineTwoNumber - lineThreeNumber;
            } else {
                remainNumber = 0;
            }
        } else {
            calculate2.setTwo(lineTwoNumber / 10);
            calculate2.setOne(lineTwoNumber % 10);

            int lineThreeNumber = lineTwoNumber / b * b;
            calculate3.setTwo(lineThreeNumber / 10);
            calculate3.setOne(lineThreeNumber % 10);

            if(lineTwoNumber-lineThreeNumber != 0){
                remainNumber = lineTwoNumber - lineThreeNumber;
            } else {
                remainNumber=0;
            }
        }

        return toDTO(calculate1, calculate2, calculate3, result, remainNumber);
    }

    private static Answer calculateLessThenTenDivision(int a, int b) {
        Calculate calculate1 = new Calculate();
        Integer remainNumber;

        if(a%b==0){
            calculate1.setTwo(a/10);
            calculate1.setOne(a%10);
            remainNumber = 0;
        } else {
            int temp=a/b*b;
            calculate1.setTwo(temp/10);
            calculate1.setOne(temp%10);
            remainNumber = a%b;
        }

        Result result = Result.from(a / b);

        return toDTO(calculate1,null,null,result,remainNumber);
    }

    private static Answer toDTO(Calculate calculate1, Calculate calculate2, Calculate calculate3, Result result, Integer remainder){
        Answer.AnswerBuilder builder = Answer.builder()
                .result(result);

        if (calculate1 != null && !calculate1.isNullDTO()) {
            builder.calculate1(calculate1);
        }
        if (calculate2 != null && !calculate2.isNullDTO()) {
            builder.calculate2(calculate2);
        }
        if (calculate3 != null && !calculate3.isNullDTO()) {
            builder.calculate3(calculate3);
        }
        if(remainder!=null){
            builder.remainder(remainder);
        }

        return builder.build();
    }

    private static void updateCalculate(Calculate calculate, int idx, int value) {
        switch (idx) {
            case 0 -> calculate.setOne(value);
            case 1 -> calculate.setTwo(value);
            case 2 -> calculate.setThree(value);
            case 3 -> calculate.setFour(value);
            default -> {}
        }
    }

    private static void updateCarry(Carry carry, int idx, int value) {
        switch (idx) {
            case 1 -> carry.setTwo(value);
            case 2 -> carry.setThree(value);
            case 3 -> carry.setFour(value);
            case 4 -> carry.setFive(value);
            default -> {}
        }
    }
    public Answer copy() {
        // 각 하위 DTO가 실제로 값이 있는지(null 여부 + 내부 null 검사) 확인 후 복사
        Result resultCopy   = (this.result      != null && this.result.getSize()         > 0) ? this.result.copy()   : null;
        Carry carry1Copy   = (this.carry1      != null && !this.carry1.isNull())             ? this.carry1.copy()   : null;
        Carry carry2Copy   = (this.carry2      != null && !this.carry2.isNull())             ? this.carry2.copy()   : null;
        Calculate calc1Copy = (this.calculate1  != null && !this.calculate1.isNullDTO())      ? this.calculate1.copy() : null;
        Calculate calc2Copy = (this.calculate2  != null && !this.calculate2.isNullDTO())      ? this.calculate2.copy() : null;
        Calculate calc3Copy = (this.calculate3  != null && !this.calculate3.isNullDTO())      ? this.calculate3.copy() : null;

        return Answer.builder()
                .result(resultCopy)
                .carry1(carry1Copy)
                .carry2(carry2Copy)
                .calculate1(calc1Copy)
                .calculate2(calc2Copy)
                .calculate3(calc3Copy)
                .remainder(this.remainder) // Integer 는 그대로 복사 (null 허용)
                .build();
    }
}

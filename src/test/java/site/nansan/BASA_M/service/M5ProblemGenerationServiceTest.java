package site.nansan.BASA_M.service;

import org.junit.jupiter.api.Test;
import site.nansan.BASA_M.dto.CalculationResponse;
import site.nansan.BASA_M.dto.answer.CalculateDTO;
import site.nansan.BASA_M.dto.answer.CarryDTO;

import static org.junit.jupiter.api.Assertions.*;

public class
M5ProblemGenerationServiceTest {

    private final M5ProblemGenerationService service = new M5ProblemGenerationService();

    @Test
    void testGenerateM5_1ProblemRange() {
        // 구구단 나눗셈의 몫 구하기
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM5_1Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int result = response.getAnswer().getResult().toNumber();

            System.out.println(a + " ÷ " + b + " = " + result);

            assertTrue(b >= 1 && b <= 9, "b is in range 1-9: " + b);

            // 결과가 a와 b의 나눗셈 몫과 일치하는지 확인
            assertEquals(a / b, result, "Result equals a ÷ b");
        }
    }

    @Test
    void testGenerateM5_2ProblemRange() {
        // 두자리 수 한자리 수 곱셈
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM5_2Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int result = response.getAnswer().getResult().toNumber();

            System.out.println(a + " × " + b + " = " + result);
            CarryDTO dto = response.getAnswer().getCarry1();
            if (dto.getOne() != null) {
                System.out.println("one: " + dto.getOne());
            }
            if (dto.getTwo() != null) {
                System.out.println("two: " + dto.getTwo());
            }
            if (dto.getThree() != null) {
                System.out.println("three: " + dto.getThree());
            }
            if (dto.getFour() != null) {
                System.out.println("four: " + dto.getFour());
            }
            if (dto.getFive() != null) {
                System.out.println("five: " + dto.getFive());
            }
            CalculateDTO calculate = response.getAnswer().getCalculate1();
            if (calculate.getFour() != null) {
                System.out.print(calculate.getFour());
            }
            if (calculate.getThree() != null) {
                System.out.print(calculate.getThree());
            }
            if (calculate.getTwo() != null) {
                System.out.print(calculate.getTwo());
            }
            if (calculate.getOne() != null) {
                System.out.println(calculate.getOne());
            }
            System.out.println("------------------");
            // a가 두자리 수인지 확인
            assertTrue(a >= 10 && a <= 99, "a is 2-digit: " + a);
            // b가 한자리 수인지 확인
            assertTrue(b >= 1 && b <= 9, "b is 1-digit: " + b);

            // 결과가 a와 b의 곱셈 결과와 일치하는지 확인
            assertEquals(a * b, result, "Result equals a × b");
        }
    }

    @Test
    void testGenerateM5_3ProblemRange() {
        // 네자리 수 덧셈
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM5_3Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int sum = response.getAnswer().getResult().toNumber();
            CarryDTO carry = response.getAnswer().getCarry1();
            if (carry.getOne() != null) {
                System.out.println("one: " + carry.getOne());
            }
            if (carry.getTwo() != null) {
                System.out.println("two: " + carry.getTwo());
            }
            if (carry.getThree() != null) {
                System.out.println("three: " + carry.getThree());
            }
            if (carry.getFour() != null) {
                System.out.println("four: " + carry.getFour());
            }
            if (carry.getFive() != null) {
                System.out.println("five: " + carry.getFive());
            }
            System.out.println(a + " + " + b + " = " + sum);
            System.out.println("--------------------------");
            // a, b가 네자리 수인지 확인
            assertTrue(a >= 1000 && a <= 9999, "a is 4-digit: " + a);
            assertTrue(b >= 1000 && b <= 9999, "b is 4-digit: " + b);
        }
    }

    @Test
    void testGenerateM5_4ProblemRange() {
        // 네자리 수 뺄셈
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM5_4Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int diff = response.getAnswer().getResult().toNumber();
            CarryDTO carry = response.getAnswer().getCarry1();

            System.out.println(a + " - " + b + " = " + diff);
            if (carry.getOne() != null) {
                System.out.println("one: " + carry.getOne());
            }
            if (carry.getTwo() != null) {
                System.out.println("two: " + carry.getTwo());
            }
            if (carry.getThree() != null) {
                System.out.println("three: " + carry.getThree());
            }
            if (carry.getFour() != null) {
                System.out.println("four: " + carry.getFour());
            }
            if (carry.getFive() != null) {
                System.out.println("five: " + carry.getFive());
            }
            System.out.println("--------------------------");
            // a, b가 네자리 수인지 확인
            assertTrue(a >= 1000 && a <= 9999, "a is 4-digit: " + a);
            assertTrue(b >= 1000 && b <= 9999, "b is 4-digit: " + b);

            // a가 b보다 큰지 확인
            assertTrue(a > b, "a is greater than b: " + a + " > " + b);

            // 결과가 a - b와 일치하는지 확인
            assertEquals(a - b, diff, "Difference equals a - b");
        }
    }
}

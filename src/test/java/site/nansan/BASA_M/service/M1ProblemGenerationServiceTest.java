package site.nansan.BASA_M.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import site.nansan.BASA_M.dto.CalculationResponse;
import site.nansan.BASA_M.dto.answer.CarryDTO;

public class M1ProblemGenerationServiceTest {

    private final M1ProblemGenerationService service = new M1ProblemGenerationService();

    @Test
    void testGenerateM1_1ProblemRange() {
        // 합이 9 이하인 덧셈: a와 b는 1자리 숫자, a + b < 10 이어야 함.
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM1_1Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int sum = response.getAnswer().getResult().getOne();
            System.out.println(a + " + "+b+" = "+sum);
            // a와 b는 1 ~ 9 범/위
            assertTrue(a >= 1 && a <= 9, "a는 1~9 사이여야 합니다. a = " + a);
            assertTrue(b >= 1 && b <= 9, "b는 1~9 사이여야 합니다. b = " + b);
            // 합이 10 미만이어야 함
            assertTrue(sum < 10, "a + b는 10 미만이어야 합니다. sum = " + sum);
            // 연산 결과 확인
            assertEquals(a + b, sum, "합이 a + b와 일치해야 합니다.");
        }
    }

    @Test
    void testGenerateM1_2ProblemRange() {
        // 한자리 수 끼리의 뺄셈: a는 최소 2 이상, b는 1부터 a-1, 결과는 a - b (최소 1 이상)
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM1_2Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int diff = response.getAnswer().getResult().getOne();
            System.out.println(a + " - "+b+" = "+diff);
            // a는 2 ~ 9, b는 1 ~ (a-1)
            assertTrue(a >= 2 && a <= 9, "a는 2~9 사이여야 합니다. a = " + a);
            assertTrue(b >= 1 && b < a, "b는 1 이상 a 미만이어야 합니다. a = " + a + ", b = " + b);
            // 결과는 a - b
            assertEquals(a - b, diff, "결과는 a - b여야 합니다.");
            // 최소 1 이상
            assertTrue(diff >= 1, "결과는 최소 1 이상이어야 합니다.");
        }
    }

    @Test
    void testGenerateM1_3ProblemRange() {
        // 일의 자리 두 수의 합이 10 이상인 덧셈: a와 b는 1자리, a + b는 최소 10, 최대 18 (9+9)
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM1_3Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int sum = response.getAnswer().getResult().toNumber();
            System.out.println(a + " + "+b+" = "+sum);
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
            System.out.println("------------------");
            // a와 b는 1 ~ 9
            assertTrue(a >= 1 && a <= 9, "a는 1~9 사이여야 합니다. a = " + a);
            assertTrue(b >= 1 && b <= 9, "b는 1~9 사이여야 합니다. b = " + b);
            // 합은 최소 10, 최대 18이어야 함
            assertTrue(sum >= 10 && sum <= 18, "합은 10 이상 18 이하여야 합니다. sum = " + sum);
            assertEquals(a + b, sum, "합은 a + b와 일치해야 합니다.");
        }
    }

    @Test
    void testGenerateM1_4ProblemRange() {
        // 10 - 몇의 뺄셈: 첫 피연산자는 10, b는 1 ~ 9, 결과는 10 - b (1~9)
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM1_4Problem();
            int fixedOperand = 10;
            int b = response.getProblem().getSecond();
            int result = response.getAnswer().getResult().toNumber();
            System.out.println(fixedOperand + " - "+b+" = "+result);
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
            System.out.println("------------------");
            // b는 1 ~ 9 범위
            assertTrue(b >= 1 && b <= 9, "b는 1~9 사이여야 합니다. b = " + b);
            // 결과는 10 - b, 즉 1 ~ 9
            assertEquals(fixedOperand - b, result, "결과는 10 - b여야 합니다.");
            assertTrue(result >= 1 && result <= 9, "결과는 1~9 사이여야 합니다. result = " + result);
        }
    }
}

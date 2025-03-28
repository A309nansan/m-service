package site.nansan.BASA_M.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import site.nansan.BASA_M.dto.CalculationResponse;
import site.nansan.BASA_M.dto.answer.CarryDTO;

public class M4ProblemGenerationServiceTest {

    private final M4ProblemGenerationService service = new M4ProblemGenerationService();

    @Test
    void testGenerateM4_1ProblemRange() {
        // 받아올림 없는 세자리 + 세자리:
        // a와 b는 100~999, 각 자리의 합이 carry 없이 (<10) 계산됨
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM4_1Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int sum = response.getAnswer().getResult().toNumber();
            System.out.println(a+" + "+b+" = "+sum);
            // a, b가 3자리 수인지 확인
            assertTrue(a >= 100 && a <= 999, "a is 3-digit: " + a);
            assertTrue(b >= 100 && b <= 999, "b is 3-digit: " + b);

            // 각 자리의 합에 대해 받아올림이 발생하지 않는지 확인
            int a_h = a / 100;
            int a_t = (a / 10) % 10;
            int a_o = a % 10;
            int b_h = b / 100;
            int b_t = (b / 10) % 10;
            int b_o = b % 10;
            assertTrue(a_h + b_h < 10, "Hundreds digit sum < 10: " + a_h + " + " + b_h);
            assertTrue(a_t + b_t < 10, "Tens digit sum < 10: " + a_t + " + " + b_t);
            assertTrue(a_o + b_o < 10, "Ones digit sum < 10: " + a_o + " + " + b_o);

            // 전체 합이 a+b와 일치하는지
            assertEquals(a + b, sum, "Sum equals a + b");
        }
    }

    @Test
    void testGenerateM4_2ProblemRange() {
        // 받아내림 없는 세자리 - 세자리:
        // a와 b는 3자리 수이며, 각 자릿수에서 a의 값이 b의 값보다 크거나 같아야 함.
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM4_2Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int diff = response.getAnswer().getResult().toNumber();
            System.out.println(a+" - "+b+" = "+diff);
            assertTrue(a >= 100 && a <= 999, "a is 3-digit: " + a);
            assertTrue(b >= 100 && b <= 999, "b is 3-digit: " + b);

            int a_h = a / 100;
            int a_t = (a / 10) % 10;
            int a_o = a % 10;
            int b_h = b / 100;
            int b_t = (b / 10) % 10;
            int b_o = b % 10;
            assertTrue(a_h >= b_h, "Hundreds digit: " + a_h + " >= " + b_h);
            assertTrue(a_t >= b_t, "Tens digit: " + a_t + " >= " + b_t);
            assertTrue(a_o >= b_o, "Ones digit: " + a_o + " >= " + b_o);
            assertEquals(a - b, diff, "Difference equals a - b");
        }
    }
    @Test
    void testGenerateM4_3ProblemRange() {
        // 받아올림 있는 세자리 + 세자리: a, b는 3자리, sum < 1000,
        // 그리고 각 자리(백, 십, 일) 중 최소 한 자리쌍의 합이 10 이상 (carry 발생)
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM4_3Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int sum = response.getAnswer().getResult().toNumber();
            System.out.println(a+" + "+b+" = "+sum);
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
            // a, b가 3자리 수인지
            assertTrue(a >= 100 && a <= 999, "a is 3-digit: " + a);
            assertTrue(b >= 100 && b <= 999, "b is 3-digit: " + b);

            // 각 자리 분리
            int a_h = a / 100;
            int a_t = (a / 10) % 10;
            int a_o = a % 10;
            int b_h = b / 100;
            int b_t = (b / 10) % 10;
            int b_o = b % 10;

            // 최소 한 자리쌍의 합이 10 이상이어야 한다.
            boolean carryOccurs = (a_h + b_h >= 10) || (a_t + b_t >= 10) || (a_o + b_o >= 10);
            assertTrue(carryOccurs, "At least one digit pair should produce a carry: " +
                    "a_h+b_h=" + (a_h+b_h) + ", a_t+b_t=" + (a_t+b_t) + ", a_o+b_o=" + (a_o+b_o));

            assertEquals(a + b, sum, "Sum equals a+b");
        }
    }

    @Test
    void testGenerateM4_4ProblemRange() {
        // 받아내림 있는 세자리 - 세자리: a, b는 3-digit, a > b,
        // 그리고 a의 십의자리 또는 일의자리에서 borrow가 발생 (즉, a의 해당 자리 < b의 해당 자리)
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM4_4Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int diff = response.getAnswer().getResult().toNumber();
            System.out.println(a+" - "+b+" = "+diff);
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
            assertTrue(a >= 100 && a <= 999, "a is 3-digit: " + a);
            assertTrue(b >= 100 && b <= 999, "b is 3-digit: " + b);
            assertTrue(a > b, "a must be greater than b: " + a + " > " + b);

            int a_t = (a / 10) % 10;
            int a_o = a % 10;
            int b_t = (b / 10) % 10;
            int b_o = b % 10;
            // borrow 발생 조건: 십의자리 또는 일의자리에서 a의 값 < b의 값
            boolean borrowOccurs = (a_t < b_t) || (a_o < b_o);
            assertTrue(borrowOccurs, "At least one digit (tens or ones) should require borrowing: " +
                    "a_t=" + a_t + ", b_t=" + b_t + ", a_o=" + a_o + ", b_o=" + b_o);

            assertEquals(a - b, diff, "Difference equals a - b");
        }
    }
}

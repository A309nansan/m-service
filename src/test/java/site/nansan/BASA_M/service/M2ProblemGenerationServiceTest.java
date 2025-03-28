package site.nansan.BASA_M.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import site.nansan.BASA_M.dto.CalculationResponse;

public class M2ProblemGenerationServiceTest {

    private final M2ProblemGenerationService service = new M2ProblemGenerationService();

    @Test
    void testGenerateM2_1ProblemRange() {
        // 받아올림 없는 두자리 + 한자리: a는 10~99, b는 1자리, 단 a의 일의 자리 + b < 10
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM2_1Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int sum = response.getAnswer().getResult().toNumber();
            System.out.println(a+" + "+b+" = "+sum);
            // a는 2자리, b는 1자리
            assertTrue(a >= 10 && a <= 99, "a is 2-digit: " + a);
            assertTrue(b >= 1 && b <= 9, "b is 1-digit: " + b);
            // a의 일의 자리 + b < 10
            int ones = a % 10;
            assertTrue(ones + b < 10, "No carry: " + ones + " + " + b + " < 10");
            assertEquals(a + b, sum, "Sum equals a + b");
        }
    }

    @Test
    void testGenerateM2_2ProblemRange() {
        // 받아내림 없는 두자리 - 한자리: a는 2-digit, b is 1-digit, 조건: a's ones digit >= b, and a - b = diff
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM2_2Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int diff = response.getAnswer().getResult().toNumber();
            System.out.println(a+" - "+b+" = "+diff);
            assertTrue(a >= 10 && a <= 99, "a is 2-digit: " + a);
            assertTrue(b >= 1 && b <= 9, "b is 1-digit: " + b);
            assertTrue(a % 10 >= b, "No borrow in ones digit: " + (a % 10) + " >= " + b);
            assertEquals(a - b, diff, "Difference equals a - b");
            assertTrue(diff >= 1, "Difference is at least 1");
        }
    }

    @Test
    void testGenerateM2_3ProblemRange() {
        // 받아올림 없는 두자리 + 두자리: a, b 모두 2-digit, 조건: (a%10) + (b%10) < 10
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM2_3Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int sum = response.getAnswer().getResult().toNumber();
            System.out.println(a+" + "+b+" = "+sum);
            assertTrue(a >= 10 && a <= 99, "a is 2-digit: " + a);
            assertTrue(b >= 10 && b <= 99, "b is 2-digit: " + b);
            assertTrue(a + b < 100, "The sum a+b must be less than 100, but was: " + (a + b));
            assertTrue((a % 10) + (b % 10) < 10, "No carry: " + (a % 10) + " + " + (b % 10) + " < 10");
            assertEquals(a + b, sum, "Sum equals a + b");
        }
    }

    @Test
    void testGenerateM2_4ProblemRange() {
        // 받아내림 없는 두자리 - 두자리: a, b 모두 2-digit, 조건: a%10 >= b%10 and a >= b
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM2_4Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int diff = response.getAnswer().getResult().toNumber();
            System.out.println(a+" - "+b+" = "+diff);
            assertTrue(a >= 10 && a <= 99, "a is 2-digit: " + a);
            assertTrue(b >= 10 && b <= 99, "b is 2-digit: " + b);
            assertTrue(a >= b, "a must be greater than or equal to b: " + a + " >= " + b);
            assertTrue(a % 10 >= b % 10, "No borrow: " + (a % 10) + " >= " + (b % 10));
            assertEquals(a - b, diff, "Difference equals a - b");
        }
    }
}

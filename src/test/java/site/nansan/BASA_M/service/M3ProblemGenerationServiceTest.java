package site.nansan.BASA_M.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import site.nansan.BASA_M.dto.CalculationResponse;
import site.nansan.BASA_M.dto.answer.CarryDTO;

public class M3ProblemGenerationServiceTest {

    private final M3ProblemGenerationService service = new M3ProblemGenerationService();

    @Test
    void testGenerateM3_1ProblemRange() {
        // 받아올림 있는 두자리 + 한자리: a (2-digit), b (1-digit) and (a % 10 + b) >= 10
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM3_1Problem();
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
            assertTrue(a >= 10 && a <= 99, "a is 2-digit: " + a);
            assertTrue(b >= 1 && b <= 9, "b is 1-digit: " + b);
            assertTrue((a % 10) + b >= 10, "Carry must occur: (" + (a % 10) + " + " + b + ") >= 10");
            assertEquals(a + b, sum, "Sum equals a + b");
        }
    }

    @Test
    void testGenerateM3_2ProblemRange() {
        // 받아내림 있는 두자리 - 한자리: a (2-digit), b (1-digit) with a % 10 < b
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM3_2Problem();
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
            assertTrue(a >= 10 && a <= 99, "a is 2-digit: " + a);
            assertTrue(b >= 1 && b <= 9, "b is 1-digit: " + b);
            assertTrue(a % 10 < b, "Borrow must occur: " + (a % 10) + " < " + b);
            assertEquals(a - b, diff, "Difference equals a - b");
        }
    }

    @Test
    void testGenerateM3_3ProblemRange() {
        // 받아올림 있는 두자리 + 두자리: a and b are 2-digit, and (a % 10) + (b % 10) >= 10
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM3_3Problem();
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
            assertTrue(a >= 10 && a <= 99, "a is 2-digit: " + a);
            assertTrue(b >= 10 && b <= 99, "b is 2-digit: " + b);
            assertTrue((a % 10) + (b % 10) >= 10, "Carry must occur: (" + (a % 10) + " + " + (b % 10) + ") >= 10");
            assertEquals(a + b, sum, "Sum equals a + b");
        }
    }

    @Test
    void testGenerateM3_4ProblemRange() {
        // 받아내림 있는 두자리 - 두자리: a and b are 2-digit, with a >= b and (a % 10) < (b % 10)
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM3_4Problem();
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
            assertTrue(a >= 10 && a <= 99, "a is 2-digit: " + a);
            assertTrue(b >= 10 && b <= 99, "b is 2-digit: " + b);
            assertTrue(a >= b, "a must be greater than or equal to b: " + a + " >= " + b);
            assertTrue(a % 10 < b % 10, "Borrow must occur: " + (a % 10) + " < " + (b % 10));
            assertEquals(a - b, diff, "Difference equals a - b");
        }
    }
}

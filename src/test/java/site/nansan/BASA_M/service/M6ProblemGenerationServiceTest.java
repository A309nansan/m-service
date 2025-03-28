package site.nansan.BASA_M.service;

import org.junit.jupiter.api.Test;
import site.nansan.BASA_M.dto.CalculationResponse;
import site.nansan.BASA_M.dto.answer.CalculateDTO;
import site.nansan.BASA_M.dto.answer.CarryDTO;

import static org.junit.jupiter.api.Assertions.*;

class M6ProblemGenerationServiceTest {
    private final M6ProblemGenerationService service = new M6ProblemGenerationService();

    @Test
    void testGenerateM6_1ProblemRange() {
        // 두자리 수 한자리 수 곱셈
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM6_1Problem();
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
            };
            // a가 두자리 수인지 확인
            assertTrue(a >= 100 && a <= 999, "a is 3-digit: " + a);
            // b가 한자리 수인지 확인
            assertTrue(b >= 1 && b <= 9, "b is 1-digit: " + b);

            // 결과가 a와 b의 곱셈 결과와 일치하는지 확인
            assertEquals(a * b, result, "Result equals a × b");
        }
    }
    @Test
    void testGenerateM6_2ProblemRange() {
        // 두자리 수 한자리 수 곱셈
        for (int i = 0; i < 100; i++) {
            CalculationResponse response = service.generateM6_2Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int result = response.getAnswer().getResult().toNumber();

            CarryDTO dto2 = response.getAnswer().getCarry2();
            CarryDTO dto = response.getAnswer().getCarry1();
            CalculateDTO calculate = response.getAnswer().getCalculate1();
            CalculateDTO calculate2 = response.getAnswer().getCalculate2();

            System.out.println("--------carry2------");
            if (dto2.getOne() != null) {
                System.out.println("one: " + dto2.getOne());
            }
            if (dto2.getTwo() != null) {
                System.out.println("two: " + dto2.getTwo());
            }
            if (dto2.getThree() != null) {
                System.out.println("three: " + dto2.getThree());
            }
            if (dto2.getFour() != null) {
                System.out.println("four: " + dto2.getFour());
            }
            if (dto2.getFive() != null) {
                System.out.println("five: " + dto2.getFive());
            }
            System.out.println("-----carry1--------");
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
            System.out.println("-----calculate1-----");
            if (calculate.getFour() != null) {
                System.out.println("four: " + calculate.getFour());
            }
            if (calculate.getThree() != null) {
                System.out.println("three: " + calculate.getThree());
            }
            if (calculate.getTwo() != null) {
                System.out.println("two: " + calculate.getTwo());
            }
            if (calculate.getOne() != null) {
                System.out.println("one: " + calculate.getOne());
            }
            System.out.println("-----calculate2-----");
            if (calculate2.getFour() != null) {
                System.out.println("four: " + calculate2.getFour());
            }
            if (calculate2.getThree() != null) {
                System.out.println("three: " + calculate2.getThree());
            }
            if (calculate2.getTwo() != null) {
                System.out.println("two: " + calculate2.getTwo());
            }
            if (calculate2.getOne() != null) {
                System.out.println("one: " + calculate2.getOne());
            }
            System.out.println("==============================");
            System.out.println(a + " × " + b + " = " + result);
            // a가 두자리 수인지 확인
            assertTrue(a >= 10 && a <= 99, "a is 2-digit: " + a);
            // b가 한자리 수인지 확인
            assertTrue(b >= 10 && b <= 99, "b is 2-digit: " + b);

            // 결과가 a와 b의 곱셈 결과와 일치하는지 확인
            assertEquals(a * b, result, "Result equals a × b");
        }
    }

    @Test
    void testGenerateM6_3ProblemRange() {
        // 두자리 수 한자리 수 곱셈
        for (int i = 0; i < 100; i++) {
            System.out.println("=============="+"test "+i+"==============");
            CalculationResponse response = service.generateM6_3Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int result = response.getAnswer().getResult().toNumber();

            System.out.println(a + " / " + b + " = " + result);
            CalculateDTO calculate = response.getAnswer().getCalculate1();
            CalculateDTO calculate2 = response.getAnswer().getCalculate2();
            CalculateDTO calculate3 = response.getAnswer().getCalculate3();

            if(calculate!=null){
                System.out.println("--------calculate1-------");
                if (calculate.getFour() != null) {
                    System.out.println("four: " + calculate.getFour());
                }
                if (calculate.getThree() != null) {
                    System.out.println("three: " + calculate.getThree());
                }
                if (calculate.getTwo() != null) {
                    System.out.println("two: " + calculate.getTwo());
                }
                if (calculate.getOne() != null) {
                    System.out.println("one: " + calculate.getOne());
                }
            }
            if(calculate2!=null) {
                System.out.println("-----calculate2-----");
                if (calculate2.getFour() != null) {
                    System.out.println("four: " + calculate2.getFour());
                }
                if (calculate2.getThree() != null) {
                    System.out.println("three: " + calculate2.getThree());
                }
                if (calculate2.getTwo() != null) {
                    System.out.println("two: " + calculate2.getTwo());
                }
                if (calculate2.getOne() != null) {
                    System.out.println("one: " + calculate2.getOne());
                }
            }
            if(calculate3!=null) {
                System.out.println("-----calculate3-----");
                if (calculate3.getFour() != null) {
                    System.out.println("four: " + calculate3.getFour());
                }
                if (calculate3.getThree() != null) {
                    System.out.println("three: " + calculate3.getThree());
                }
                if (calculate3.getTwo() != null) {
                    System.out.println("two: " + calculate3.getTwo());
                }
                if (calculate3.getOne() != null) {
                    System.out.println("one: " + calculate3.getOne());
                }
            }
            System.out.println("나머지 : "+ response.getAnswer().getRemainder());

            // a와 b의 곱셈 결과와 일치하는지 확인
            assertEquals(a / b, result, "Result equals a / b");
            assertEquals(0, response.getAnswer().getRemainder(), "Result should not have remain number");

        }
    }

    @Test
    void testGenerateM6_4ProblemRange() {
        // 나머지가 있는 나눗셈
        for (int i = 0; i < 100; i++) {
            System.out.println("=============="+"test "+i+"==============");
            CalculationResponse response = service.generateM6_4Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int result = response.getAnswer().getResult().toNumber();

            System.out.println(a + " / " + b + " = " + result+"  ... "+ response.getAnswer().getRemainder());
            CalculateDTO calculate = response.getAnswer().getCalculate1();
            CalculateDTO calculate2 = response.getAnswer().getCalculate2();
            CalculateDTO calculate3 = response.getAnswer().getCalculate3();

            if(calculate!=null) {
                System.out.println("--------calculate1-------");
                if (calculate.getFour() != null) {
                    System.out.println("four: " + calculate.getFour());
                }
                if (calculate.getThree() != null) {
                    System.out.println("three: " + calculate.getThree());
                }
                if (calculate.getTwo() != null) {
                    System.out.println("two: " + calculate.getTwo());
                }
                if (calculate.getOne() != null) {
                    System.out.println("one: " + calculate.getOne());
                }
            }
            if(calculate2!=null) {
                System.out.println("-----calculate2-----");
                if (calculate2.getFour() != null) {
                    System.out.println("four: " + calculate2.getFour());
                }
                if (calculate2.getThree() != null) {
                    System.out.println("three: " + calculate2.getThree());
                }
                if (calculate2.getTwo() != null) {
                    System.out.println("two: " + calculate2.getTwo());
                }
                if (calculate2.getOne() != null) {
                    System.out.println("one: " + calculate2.getOne());
                }
            }

            if(calculate3!=null) {
                System.out.println("-----calculate3-----");
                if (calculate3.getFour() != null) {
                    System.out.println("four: " + calculate3.getFour());
                }
                if (calculate3.getThree() != null) {
                    System.out.println("three: " + calculate3.getThree());
                }
                if (calculate3.getTwo() != null) {
                    System.out.println("two: " + calculate3.getTwo());
                }
                if (calculate3.getOne() != null) {
                    System.out.println("one: " + calculate3.getOne());
                }
            }
            System.out.println("나머지 : "+ response.getAnswer().getRemainder());

            assertEquals(a / b, result, "Result equals a / b");
            assertEquals(a % b, response.getAnswer().getRemainder(), "Result equals a % b");
        }
    }  @Test
    void testGenerateM6_5ProblemRange() {
        // 두자리 수 한자리 수 곱셈
        for (int i = 0; i < 100; i++) {
            System.out.println("=============="+"test "+i+"==============");
            CalculationResponse response = service.generateM6_5Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int result = response.getAnswer().getResult().toNumber();

            System.out.println(a + " / " + b + " = " + result);
            CalculateDTO calculate = response.getAnswer().getCalculate1();
            CalculateDTO calculate2 = response.getAnswer().getCalculate2();
            CalculateDTO calculate3 = response.getAnswer().getCalculate3();

            if(calculate!=null) {
                System.out.println("--------calculate1-------");
                if (calculate.getFour() != null) {
                    System.out.println("four: " + calculate.getFour());
                }
                if (calculate.getThree() != null) {
                    System.out.println("three: " + calculate.getThree());
                }
                if (calculate.getTwo() != null) {
                    System.out.println("two: " + calculate.getTwo());
                }
                if (calculate.getOne() != null) {
                    System.out.println("one: " + calculate.getOne());
                }
            }
            if(calculate2!=null) {
                System.out.println("-----calculate2-----");
                if (calculate2.getFour() != null) {
                    System.out.println("four: " + calculate2.getFour());
                }
                if (calculate2.getThree() != null) {
                    System.out.println("three: " + calculate2.getThree());
                }
                if (calculate2.getTwo() != null) {
                    System.out.println("two: " + calculate2.getTwo());
                }
                if (calculate2.getOne() != null) {
                    System.out.println("one: " + calculate2.getOne());
                }
            }
            if(calculate3!=null) {
                System.out.println("-----calculate3-----");
                if (calculate3.getFour() != null) {
                    System.out.println("four: " + calculate3.getFour());
                }
                if (calculate3.getThree() != null) {
                    System.out.println("three: " + calculate3.getThree());
                }
                if (calculate3.getTwo() != null) {
                    System.out.println("two: " + calculate3.getTwo());
                }
                if (calculate3.getOne() != null) {
                    System.out.println("one: " + calculate3.getOne());
                }
            }
            System.out.println("나머지 : "+ response.getAnswer().getRemainder());

            // a와 b의 곱셈 결과와 일치하는지 확인
            assertEquals(a / b, result, "Result equals a / b");
            assertEquals(0, response.getAnswer().getRemainder(), "Result should not have remain number");

        }
    }

    @Test
    void testGenerateM6_6ProblemRange() {
        // 나머지가 있는 나눗셈
        for (int i = 0; i < 100; i++) {
            System.out.println("=============="+"test "+i+"==============");
            CalculationResponse response = service.generateM6_6Problem();
            int a = response.getProblem().getFirst();
            int b = response.getProblem().getSecond();
            int result = response.getAnswer().getResult().toNumber();

            System.out.println(a + " / " + b + " = " + result+"  ... "+ response.getAnswer().getRemainder());
            CalculateDTO calculate = response.getAnswer().getCalculate1();
            CalculateDTO calculate2 = response.getAnswer().getCalculate2();
            CalculateDTO calculate3 = response.getAnswer().getCalculate3();

            if(calculate!=null) {
                System.out.println("--------calculate1-------");
                if (calculate.getFour() != null) {
                    System.out.println("four: " + calculate.getFour());
                }
                if (calculate.getThree() != null) {
                    System.out.println("three: " + calculate.getThree());
                }
                if (calculate.getTwo() != null) {
                    System.out.println("two: " + calculate.getTwo());
                }
                if (calculate.getOne() != null) {
                    System.out.println("one: " + calculate.getOne());
                }
            }
            if(calculate!=null) {
                System.out.println("-----calculate2-----");
                if (calculate2.getFour() != null) {
                    System.out.println("four: " + calculate2.getFour());
                }
                if (calculate2.getThree() != null) {
                    System.out.println("three: " + calculate2.getThree());
                }
                if (calculate2.getTwo() != null) {
                    System.out.println("two: " + calculate2.getTwo());
                }
                if (calculate2.getOne() != null) {
                    System.out.println("one: " + calculate2.getOne());
                }
            }
            if(calculate!=null) {
                System.out.println("-----calculate3-----");
                if (calculate3.getFour() != null) {
                    System.out.println("four: " + calculate3.getFour());
                }
                if (calculate3.getThree() != null) {
                    System.out.println("three: " + calculate3.getThree());
                }
                if (calculate3.getTwo() != null) {
                    System.out.println("two: " + calculate3.getTwo());
                }
                if (calculate3.getOne() != null) {
                    System.out.println("one: " + calculate3.getOne());
                }
            }
            System.out.println("나머지 : "+ response.getAnswer().getRemainder());

            assertEquals(a / b, result, "Result equals a / b");
            assertEquals(a % b, response.getAnswer().getRemainder(), "Result equals a % b");
        }
    }
}
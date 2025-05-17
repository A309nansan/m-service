package site.nansan.BASA_M.global.util;

import site.nansan.BASA_M.global.exception.ProblemGenerationErrorCode;
import site.nansan.BASA_M.global.exception.ProblemGenerationException;

import java.util.Random;

public class RandomUtil {
    private static final Random RANDOM = new Random();

    public static final int ONE_DIGIT_MIN = 1;
    public static final int ONE_DIGIT_MAX = 9;

    /** n 자리수 랜덤 숫자 생성 */
    public static int generateRandomNDigitInt(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n은 양수여야 합니다.");
        }
        if (n == 1) {
            return RANDOM.nextInt(ONE_DIGIT_MAX) + ONE_DIGIT_MIN;
        }
        int min = (int) Math.pow(10, n - 1);
        int max = (int) Math.pow(10, n) - 1;

        return RANDOM.nextInt(max - min + 1) + min;
    }

    /** upperBound 이하 n 자리수 랜덤 숫자 생성 */
    public static int generateRandomNDigitIntLessThan(int n, int upperBound) {
        if(n <= 0) {
            throw new ProblemGenerationException(ProblemGenerationErrorCode.INVALID_N_VALUE);
        }
        int min = (int) Math.pow(10, n - 1);
        int max = (int) Math.pow(10, n) - 1;

        if (upperBound < min || upperBound > max ) {
            throw new ProblemGenerationException(ProblemGenerationErrorCode.INVALID_UPPER_BOUND);
        }

        return RANDOM.nextInt(upperBound) + min;
    }

    /** lowerBound 이상, upperBound 이하 랜덤 숫자 생성 */
    public static int generateRandomIntBetween(int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            throw new ProblemGenerationException(ProblemGenerationErrorCode.INVALID_LOWER_BOUND);
        }
        return RANDOM.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }
}

package site.nansan.BASA_M.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProblemGenerationErrorCode implements ErrorCode{
    INCORRECT_PROBLEM_CODE(HttpStatus.BAD_REQUEST, "Problem_400_1", "요청하신 문제 유형은 존재하지 않습니다."),
    INVALID_N_VALUE(HttpStatus.BAD_REQUEST, "RANDOMUTIL_400_1", "n 자리수를 생성할때 n은 양수여야 합니다."),
    INVALID_UPPER_BOUND(HttpStatus.BAD_REQUEST, "RANDOMUTIL_400_2", "upperBound 값은 지정된 n자리수 범위 내에 있어야 합니다."),
    INVALID_LOWER_BOUND(HttpStatus.BAD_REQUEST, "RANDOMUTIL_400_3", "lowerBound는 upperBound보다 작거나 같아야 합니다.");
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}

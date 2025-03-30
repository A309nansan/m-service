package site.nansan.BASA_M.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProblemErrorCode {
    INCORRECT_OPERATION("Problem_400_1", "잘못된 연산 방법으로 문제 해결"),
    LARGER_MINUS_SMALLER("Problem_400_2", "큰 수에서 작은 수를 빼는 오류"),
    ALGORITHM_MIXED_ERROR("Problem_400_3", "알고리즘 섞인 오류"),
    CARRY_ERROR("Problem_400_4", "받아올림/받아내림 오류"),
    WRONG_POSITION("Problem_400_5", "잘못된 자리 순서"),
    CALCULATE_WRONG("Problem_400_6", "계산 실수");

    private final String code;
    private final String message;
}


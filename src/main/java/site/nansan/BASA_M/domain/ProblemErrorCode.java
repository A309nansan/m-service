package site.nansan.BASA_M.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

    @Getter
    @AllArgsConstructor
    public enum ProblemErrorCode {
        INCORRECT_OPERATION_1("Problem_400_1", "덧셈문제를 뺄셈문제로 계산했어요. 이번 차시의 덧셈 편과 뺄셈 편을 복습해보세요!"),
        INCORRECT_OPERATION_2("Problem_400_2", "덧셈문제를 곱셈문제로 계산했어요. 이번 차시의 덧셈 편과 5과의 곱셈 편을 복습해보세요!"),
        INCORRECT_OPERATION_3("Problem_400_3", "뺄셈문제를 덧셈문제로 계산했어요. 이번 차시의 덧셈 편과 뺄셈 편을 복습해보세요!"),
        INCORRECT_OPERATION_4("Problem_400_4", "뺄셈문제를 곱셈문제로 계산했어요. 이번 차시의 뺄셈 편과 5과의 곱셈 편을 복습해보세요!"),
        INCORRECT_OPERATION_5_1("Problem_400_5", "곱셈 문제를 덧셈 문제로 계산했어요. 5과의 곱셈 문제를 복습해보세요!"),
        INCORRECT_OPERATION_5_2("Problem_400_6", "곱셈 문제를 덧셈 문제로 계산했어요. 6과의 곱셈 문제를 복습해보세요!"),
        INCORRECT_OPERATION_6_1("Problem_400_7", "곱셈 문제를 뺄셈 문제로 계산했어요. 5과의 곱셈 문제를 복습해보세요!"),
        INCORRECT_OPERATION_6_2("Problem_400_8", "곱셈 문제를 뺄셈 문제로 계산했어요. 6과의 곱셈 문제를 복습해보세요!"),

        LARGER_MINUS_SMALLER("Problem_400_2", "큰 수에서 작은 수를 뺏어요. 3과를 복습해보세요!"),
        ALGORITHM_MIXED_ERROR("Problem_400_3", "덧셈 문제를 곱셈처럼 2번째 숫자와 모두 더했어요. 이는 주로 곱셈을 공부한 아이들한테 자주 나타나는 현상이에요. 이번 차시의 덧셈 편과 5과의 곱셈 편을 복습해보세요!"),
        CARRY_ERROR("Problem_400_4", "받아올림/받아내림 오류"),
        CARRY_ERROR_1("Problem_400_4_1", "받아올림 부분에서 연산 실수를 했어요. 3과의 더하기 부분을 복습해보세요!"),
        CARRY_ERROR_2("Problem_400_4_2", "받아올림 부분에서 연산 실수를 했어요. 3과의 빼기 부분을 복습해보세요!"),
        CARRY_ERROR_3("Problem_400_4_3", "받아올림 부분에서 연산 실수를 했어요. 5과의 '두 자릿수와 한 자릿수의 곱셈' 부분을 복습해보세요!"),
        WRONG_POSITION("Problem_400_5", "연산 결과를 다른 칸에 작성했어요."),
        CALCULATE_WRONG("Problem_400_6", "계산 실수했어요. 실수가 반복된다면 이번 차시의 '교사와 함께하기' 부분을 선생님과 한번 더 풀어보세요!");

        private final String code;
        private final String message;
    }


package site.nansan.BASA_M.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AnswerSubmissionErrorCode implements ErrorCode{
    NUMBER_CANNOT_MINUS(HttpStatus.UNPROCESSABLE_ENTITY, "Util_400_1", "숫자가 0보다 작으면 안됩니다.."),
    TEAM_VALIDATION_COUNT_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, "Util_400_2", "참여 팀 수는 0~255 사이여야 합니다."),
    SEQUENCE_VALIDATION_COUNT_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, "Util_400_3", "스프린트 수는 0~255 사이여야 합니다."),
    STATUS_VALIDATION_COUNT_FAILED(HttpStatus.UNPROCESSABLE_ENTITY, "Util_400_4", "상태 수는 0~3 사이여야 합니다.")
    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}

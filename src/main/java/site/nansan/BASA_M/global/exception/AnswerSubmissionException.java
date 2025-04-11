package site.nansan.BASA_M.global.exception;

public class AnswerSubmissionException extends NANSANException{
    public AnswerSubmissionException(AnswerSubmissionErrorCode errorCode) {
        super(errorCode);
    }
}

package site.nansan.BASA_M.global.exception;

public class ExternalServerTooManyRequestsException extends NANSANException {
    public ExternalServerTooManyRequestsException(ErrorCode errorCode) {
        super(errorCode);
    }
}


package site.nansan.BASA_M.global.exception;

public class ExternalServerForbiddenException extends NANSANException {
    public ExternalServerForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}

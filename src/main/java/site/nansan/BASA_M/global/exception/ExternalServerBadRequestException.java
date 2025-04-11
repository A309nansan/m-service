package site.nansan.BASA_M.global.exception;

public class ExternalServerBadRequestException extends NANSANException {
    public ExternalServerBadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}

package site.nansan.BASA_M.global.exception;

public class ExternalServerUnauthorizedException extends NANSANException {
    public ExternalServerUnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}

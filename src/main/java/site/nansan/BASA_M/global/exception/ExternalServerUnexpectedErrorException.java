package site.nansan.BASA_M.global.exception;

public class ExternalServerUnexpectedErrorException extends NANSANException {
    public ExternalServerUnexpectedErrorException(ErrorCode errorCode) {
        super(errorCode);
    }
}

package site.nansan.BASA_M.global.exception;

public class ExternalServerNotFoundException extends NANSANException {
    public ExternalServerNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

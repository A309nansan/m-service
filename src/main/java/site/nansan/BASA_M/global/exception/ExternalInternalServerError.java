package site.nansan.BASA_M.global.exception;

public class ExternalInternalServerError extends NANSANException {
    public ExternalInternalServerError(ErrorCode errorCode) {
        super(errorCode);
    }
}

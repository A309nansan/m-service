package site.nansan.BASA_M.global.exception;

public class InvalidDataException extends NANSANException {
    public InvalidDataException(ErrorCode errorCode) {
        super(errorCode);
    }
}

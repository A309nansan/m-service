package site.nansan.BASA_M.global.exception;

public class DBConnectionException extends NANSANException {
    public DBConnectionException() {
        super(GlobalErrorCode.MongoDB_UNEXPECTED_ERROR);
    }
}

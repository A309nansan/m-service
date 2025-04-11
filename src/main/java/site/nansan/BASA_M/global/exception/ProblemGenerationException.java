package site.nansan.BASA_M.global.exception;

public class ProblemGenerationException extends NANSANException{

    public ProblemGenerationException(ProblemGenerationErrorCode errorCode) {
        super(errorCode);
    }
}

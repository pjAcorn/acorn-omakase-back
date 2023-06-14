package acorn.omakase.common.exception;


import acorn.omakase.common.code.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessExceptionHandler extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessExceptionHandler(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

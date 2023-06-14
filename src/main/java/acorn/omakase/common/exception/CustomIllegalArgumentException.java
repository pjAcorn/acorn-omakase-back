package acorn.omakase.common.exception;

import acorn.omakase.common.code.ErrorCode;
import lombok.Getter;

@Getter
public class CustomIllegalArgumentException extends IllegalArgumentException{

    private final ErrorCode errorCode;

    public CustomIllegalArgumentException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

package acorn.omakase.common.response;

import acorn.omakase.common.code.ErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int status;
    private String divisionCode;
    private String message;
    private String reason;

    protected ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.divisionCode = errorCode.getDivisionCode();
        this.message = errorCode.getMessage();
    }

    protected ErrorResponse(ErrorCode errorCode, String reason) {
        this.status = errorCode.getStatus();
        this.divisionCode = errorCode.getDivisionCode();
        this.message = errorCode.getMessage();
        this.reason = reason;
    }

    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }

    public static ErrorResponse of(ErrorCode errorCode, String reason) {
        return new ErrorResponse(errorCode, reason);
    }
}

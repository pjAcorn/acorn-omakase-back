package acorn.omakase.common.response;

import acorn.omakase.common.code.SuccessCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
public class ApiResponse {

    private String code;
    private String message;

    public ApiResponse(SuccessCode successCode) {
        this.code = successCode.getCode();
        this.message = successCode.getMessage();
    }

}
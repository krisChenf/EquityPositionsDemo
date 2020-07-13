package com.equity.position.domain.exception;

import lombok.Data;

/**
 * Created by Administrator on 2020/7/12.
 */

@Data
public class ErrorMessage {
    private static final long serialVersionUID = 8065583911104112360L;
    private String errorCode;
    private String errorMessage;

    public ErrorMessage(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorMessage() {
        super();
    }

}

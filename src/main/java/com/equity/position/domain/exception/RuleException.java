package com.equity.position.domain.exception;

import static com.equity.position.domain.exception.BizErrorCodeEnum.UNSPECIFIED;

/**
 * Created by Administrator on 2020/7/12.
 */
public class RuleException  extends RuntimeException {
    private static final long serialVersionUID = -7864604160297181941L;

    protected final ErrorCode enumError;

    public RuleException() {
        super(UNSPECIFIED.getDescription());
        this.enumError = UNSPECIFIED;
    }

    public RuleException(final ErrorCode enumError) {
        super(enumError.getDescription());
        this.enumError = enumError;
    }

    public RuleException(final String detailedMessage) {
        super(detailedMessage);
        this.enumError = UNSPECIFIED;
    }

    public RuleException(final Throwable t) {
        super(t);
        this.enumError = UNSPECIFIED;
    }
    public RuleException(final ErrorCode enumError, final String detailedMessage) {
        super(detailedMessage);
        this.enumError = enumError;
    }

    public RuleException(final ErrorCode enumError, final Throwable t) {
        super(enumError.getDescription(), t);
        this.enumError = enumError;
    }

    public RuleException(final String detailedMessage, final Throwable t) {
        super(detailedMessage, t);
        this.enumError = UNSPECIFIED;
    }

    public RuleException(final ErrorCode enumError, final String detailedMessage,
                         final Throwable t) {
        super(detailedMessage, t);
        this.enumError = enumError;
    }

    public ErrorCode getEnumError() {
        return enumError;
    }

}

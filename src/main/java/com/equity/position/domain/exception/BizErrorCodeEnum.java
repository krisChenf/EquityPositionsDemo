package com.equity.position.domain.exception;

import org.thymeleaf.util.StringUtils;

/**
 * Created by Administrator on 2020/7/12.
 */
public enum BizErrorCodeEnum implements ErrorCode {
    UNSPECIFIED("500", "内部异常，请稍后再试"),
    SAME_VERSION_ERROR("1001", "INSERT / UPDATE / CANCEL are actions on a Trade (with same trade id but different version)"),
    INSERT_CANCEL_ERROR("1002", "INSERT will always be 1st version of a Trade, CANCEL will always be last version of Trade"),
    UPDATE_ERROR("1004", "For UPDATE, SecurityCode or Quantity or Buy/Sell can change")
    ;

    /** 错误码 */
    private final String code;

    /** 描述 */
    private final String description;

    /**
     * @param code 错误码
     * @param description 描述
     */
    private BizErrorCodeEnum(final String code, final String description) {
        this.code = code;
        this.description = description;
    }

    public static BizErrorCodeEnum getByCode(String code) {
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return value;
            }
        }
        return UNSPECIFIED;
    }

    public static Boolean contains(String code){
        for (BizErrorCodeEnum value : BizErrorCodeEnum.values()) {
            if (StringUtils.equals(code, value.getCode())) {
                return true;
            }
        }
        return  false;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

}

package com.equity.position.interfaces.dto;

import com.equity.position.domain.vo.TransactionTypeEnum;
import com.equity.position.domain.vo.ActionEnum;
import com.equity.position.domain.vo.TransactionVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long transactionID;
    private Integer tradeId;
    private Integer version;
    private String securityCode;
    private BigDecimal quantity;
    private ActionEnum action;
    private TransactionTypeEnum type;

    public static TransactionDTO from(TransactionVO vo){
        TransactionDTO dto = new TransactionDTO();
        dto.setAction(Enum.valueOf(ActionEnum.class, vo.getAction().toUpperCase()));
        dto.setType(Enum.valueOf(TransactionTypeEnum.class, vo.getType().toUpperCase()));
        dto.setTradeId(vo.getTradeId());
        dto.setQuantity(new BigDecimal(vo.getQuantity()));
        dto.setVersion(vo.getVersion());
        dto.setSecurityCode(vo.getSecurityCode());
        return dto;
    }

}
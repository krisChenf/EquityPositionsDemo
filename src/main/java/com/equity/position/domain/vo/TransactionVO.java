package com.equity.position.domain.vo;

import com.equity.position.interfaces.dto.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionVO {

    private Long transactionID;
    @NotNull(message = "tradeId not null")
    private Integer tradeId;
    @NotNull
    private Integer version;
    @NotBlank
    private String securityCode;
    @NotBlank
    private String quantity;
    @NotBlank
    private String action;
    @NotBlank
    private String type;
    public static TransactionVO from(TransactionDTO dto){
        TransactionVO vo = new TransactionVO();
        vo.setAction(dto.getAction().name());
        vo.setType(dto.getType().name());
        vo.setVersion(dto.getVersion());
        vo.setTradeId(dto.getTradeId());
        vo.setQuantity(String.valueOf(dto.getQuantity()));
        vo.setSecurityCode(dto.getSecurityCode());
        return vo;
    }
}
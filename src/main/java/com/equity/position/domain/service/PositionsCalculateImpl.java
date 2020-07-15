package com.equity.position.domain.service;

import com.equity.position.domain.repository.TransactionRepository;
import com.equity.position.domain.vo.ActionEnum;
import com.equity.position.interfaces.dto.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import static com.equity.position.domain.vo.ActionEnum.CANCEL;
import static com.equity.position.domain.vo.TransactionTypeEnum.BUY;
import static com.equity.position.domain.vo.TransactionTypeEnum.SELL;

/**
 * Created by Administrator on 2020/7/12.
 */

@Service
public class PositionsCalculateImpl implements PositionsCalculate {
    private final static Logger logger = LoggerFactory.getLogger(PositionsCalculateImpl.class);
    @Autowired
    private TransactionRepository repository;

    @Override
    public Map<String, Object> calculate(TransactionDTO transaction) {
        logger.info("start transaction quantity calculate");
        Map<String, Object> result = new HashMap<>();
        result.put("securityCode", transaction.getSecurityCode());
        if (transaction.getAction().equals(ActionEnum.CANCEL)) {
            result.put("quantity", BigDecimal.ZERO);
            return result;
        }
        BigDecimal buy = repository.findQuantityBySecurityCodeAndType(transaction.getSecurityCode(), BUY.name(), CANCEL.name());
        BigDecimal sell = repository.findQuantityBySecurityCodeAndType(transaction.getSecurityCode(), SELL.name(), CANCEL.name());
        buy = buy == null ? BigDecimal.ZERO : buy;
        sell = sell == null ? BigDecimal.ZERO : sell;
        result.put("quantity", buy.subtract(sell).intValue());
        return result;
    }

}
